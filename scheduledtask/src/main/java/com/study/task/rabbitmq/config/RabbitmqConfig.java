package com.study.task.rabbitmq.config;

import com.study.task.rabbitmq.listener.SimpleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbitMQ配置类
 *
 * @author ziyuan_deng
 * @create 2020-06-03 21:41
 */
@Configuration
public class RabbitmqConfig {

    private static final Logger log= LoggerFactory.getLogger(RabbitmqConfig.class);

    @Autowired
    private Environment env;

    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    /**
     *配置单一消费者
     * @return
     */
   @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        factory.setBatchSize(1);
        return factory;
    }

    /**
     * 配置多个消费者
     * @return
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory,connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.concurrency",Integer.class));
        factory.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.max-concurrency",Integer.class));
        factory.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.simple.prefetch",Integer.class));
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReceiveTimeout(10000L);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
            }
        });

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
            }
        });

        return rabbitTemplate;
    }
    //基础测试队列设置
    @Bean
    public DirectExchange basicDirectExchange(){
        return  new DirectExchange(env.getProperty("basic.info.mq.exchange.name"));
    }
    @Bean("basicQueue")
    public Queue basicQueue(){
        String name = env.getProperty("basic.info.mq.queue.name");
        return new Queue(env.getProperty("basic.info.mq.queue.name"),true);
    }
    @Bean
    public Binding basicBinding(){
        return BindingBuilder.bind(basicQueue())
                .to(basicDirectExchange())
                .with(env.getProperty("basic.info.mq.routing.key.name"));
    }

    //抢购队列有关设置
    @Bean
    public DirectExchange robbingDirectExchange(){
        return new DirectExchange(env.getProperty("product.robbing.mq.exchange.name"));
    }
    @Bean(name = "robbingQueue")
    public Queue robbingQueue(){
        return new Queue(env.getProperty("product.robbing.mq.queue.name"));
    }
    @Bean
    public Binding robbingBinding(){
        String key = env.getProperty("product.robbing.mq.routing.key.name");
        return BindingBuilder.bind(robbingQueue())
                 .to(robbingDirectExchange())
                 .with(env.getProperty("product.robbing.mq.routing.key.name"));
    }

    @Bean
    public TopicExchange simpleTopicExchange(){
        return new TopicExchange(env.getProperty("simple.mq.exchange.name"));
    }
    @Bean(name = "simpleQueue")
    public Queue simpleQueue(){
        return new Queue(env.getProperty("simple.mq.queue.name"));
    }
    @Bean
    public Binding simpleBinding(){
        return BindingBuilder.bind(simpleQueue()).to(simpleTopicExchange()).with(env.getProperty("simple.mq.routing.key.name"));
    }


    @Autowired
    private SimpleListener simpleListener;
    @Bean(name = "simpleContainer")
    public SimpleMessageListenerContainer simpleCpmtainer(@Qualifier("simpleQueue") Queue simpleQueue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
       // container.setMessagePropertiesConverter(new Jackson2JsonMessageConverter());
        container.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.concurrency",Integer.class));
        container.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.max-concurrency",Integer.class));
        container.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.simple.prefetch",Integer.class));

        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.addQueues(simpleQueue);
        container.setMessageListener(simpleListener);
        return container;
    }

    /**
     * 设置死信队列，交换机，路由
     * @return
     */
    @Bean
    public Queue userOrderDeadQueue(){
        Map<String,Object> args = new HashMap<>(4);
        args.put("x-dead-letter-exchange",env.getProperty("user.order.dead.exchange.name"));
        args.put("x-dead-letter-routing-key",env.getProperty("user.order.dead.routing.key.name"));
        //args.put("x-message-ttl",10000);
        return new Queue(env.getProperty("user.order.dead.queue.name"),true,false,false,args);
    }

    /**
     * 生产的交换机
     * @return
     */
    @Bean
    public TopicExchange userOrderTopicExchange(){
        return new TopicExchange(env.getProperty("user.order.dead.produce.exchange.name"),true,false);
    }

    /**
     * 生产的交换机和路由绑定到死信队列
     * @return
     */
    @Bean
    public Binding userOrderBingding(){

        return BindingBuilder.bind(userOrderDeadQueue())
                .to(userOrderTopicExchange())
                .with(env.getProperty("user.order.dead.produce.routing.key.name"));
    }

    /**
     * 生成实际接收消息的队列
     * @return
     */
    @Bean
    public Queue userOrderRealQueue(){
        return  new Queue(env.getProperty("user.order.dead.produce.queue.name"),true,false,false);
    }

    /**
     * 死信交换机
     * @return
     */
    @Bean
    public TopicExchange userOrderRealTopicExchange(){
        return new TopicExchange(env.getProperty("user.order.dead.exchange.name"),true,false);
    }

    /**
     * 死信路由
     * @return
     */
    @Bean
    public Binding userOrderRealBinding(){
        return BindingBuilder.bind(userOrderRealQueue())
                .to(userOrderRealTopicExchange())
                .with(env.getProperty("user.order.dead.routing.key.name"));
    }

}
