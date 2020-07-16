package com.study.task.rabbitmq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.task.rabbitmq.model.User;
import com.study.task.scheduledtask.common.Response;
import com.study.task.scheduledtask.utils.ResponseUtil;
import org.assertj.core.util.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbitmq测试
 *
 * @author ziyuan_deng
 * @create 2020-06-04 20:26
 */
@RestController
public class RabbitController {

    private static final Logger log= LoggerFactory.getLogger(RabbitController.class);
    @Autowired
    private Environment env;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/simple/message")
    public Response sendSimpleMessage(){
        try {
            // String message = "海阔天空";
           /* User user = new User();
            user.setId(1);
            user.setName("拼命三郎");
            user.setUserName("农小明");
            log.info("待发送的消息： {} ",user);*/
            User user = User.builder().age(18).jobNum("123").name("李四").build();
            //String mes = "发送消息！！！";
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(env.getProperty("basic.info.mq.exchange.name"));
            rabbitTemplate.setRoutingKey(env.getProperty("basic.info.mq.routing.key.name"));

            //Message msg = MessageBuilder.withBody(objectMapper.writeValueAsBytes(user)).build();

           //rabbitTemplate.send(msg);
            rabbitTemplate.convertAndSend(user);

            return ResponseUtil.success("发送成功！！！！");

        }catch (Exception e){
            log.error("发送简单消息发生异常： ",e.fillInStackTrace());
            return ResponseUtil.exception();
        }
    }

    @GetMapping("/simple/send")
    public Response sendUser(){

        try {
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(env.getProperty("simple.mq.exchange.name"));
            rabbitTemplate.setRoutingKey(env.getProperty("simple.mq.routing.key.name"));
            String str ="我爱你啊";
            Message message = MessageBuilder.withBody(objectMapper.writeValueAsBytes(str))
                    .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                    .build();
            rabbitTemplate.convertAndSend(message);
        }catch (Exception e){
            log.error("发送消息异常：",e.fillInStackTrace());
        }
        return ResponseUtil.success("发送成功");
    }
}
