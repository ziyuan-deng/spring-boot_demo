package com.study.task.rabbitmq.service;

import com.study.task.rabbitmq.controller.RabbitController;
import com.study.task.rabbitmq.mapper.ProductMapper;
import com.study.task.rabbitmq.model.Product;
import com.study.task.rabbitmq.model.ProductExample;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 抢单消息队列处理
 *
 * @author ziyuan_deng
 * @create 2020-06-05 13:02
 */
@Service
public class RobbingMqService {

    private static final Logger log= LoggerFactory.getLogger(RobbingMqService.class);

    @Autowired
    private Environment env;
    @Autowired
    private RabbitTemplate template;
    @Resource
    private ProductMapper productMapper;

    private static final String PRODUCT_NO = "product_10010";

    /**
     * 异步发布抢单信息
     * @param mobile
     */
    public void sendRobbingMsg(Integer mobile) {

        try {
            template.setMessageConverter(new Jackson2JsonMessageConverter());
            template.setExchange(env.getProperty("product.robbing.mq.exchange.name"));
            template.setRoutingKey(env.getProperty("product.robbing.mq.routing.key.name"));
           // template.set
            if (mobile!=null) {
                ProductExample example = new ProductExample();
                example.createCriteria().andProductNoEqualTo(PRODUCT_NO);
                List<Product> products = productMapper.selectByExample(example);
                if (CollectionUtils.isNotEmpty(products)) {
                    Product product = products.get(0);
                    if (product.getTotal()>0) {
                        template.convertAndSend(mobile);
                        log.info(" 抢单消息发送成功，手机号为：{}",mobile);
                    }
                }
            }else{
                return;
            }
        }catch (Exception e){
            log.error("消息发送异常：",e);
        }

    }
}
