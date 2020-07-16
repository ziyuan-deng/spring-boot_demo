package com.study.task.rabbitmq.controller;

import com.study.task.rabbitmq.model.OrderRecord;
import com.study.task.rabbitmq.service.OrderService;
import com.study.task.scheduledtask.common.Response;
import com.study.task.scheduledtask.utils.ResponseUtil;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 订单业务
 *
 * @author ziyuan_deng
 * @create 2020-07-15 17:15
 */
@RestController
public class UserOrderController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Resource
    private OrderService orderService;
    @Autowired
    private Environment env;

    @PostMapping("/order/push")
    public Response pushOrder(@RequestBody OrderRecord orderRecord){

        try {
            orderRecord.setCreateTime(new Date());
            orderRecord.setUpdateTime(new Date());
            orderRecord.setStatus(1);
            int count = orderService.saveOrderInfo(orderRecord);
            long ttl = 10000L;
            if (count>0){
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                rabbitTemplate.setExchange(env.getProperty("user.order.dead.produce.exchange.name"));
                rabbitTemplate.setRoutingKey(env.getProperty("user.order.dead.produce.routing.key.name"));
                rabbitTemplate.convertAndSend(orderRecord.getId(), new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        MessageProperties properties = message.getMessageProperties();
                        properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        properties.setHeader(AbstractJavaTypeMapper.DEFAULT_KEY_CLASSID_FIELD_NAME,Integer.class);
                        properties.setExpiration(String.valueOf(ttl));
                        return message;
                    }
                });
            }
        }catch (Exception e){
            ResponseUtil.exception();
        }
      return ResponseUtil.success("保存成功");
    }

}
