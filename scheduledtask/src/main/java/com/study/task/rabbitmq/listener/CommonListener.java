package com.study.task.rabbitmq.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.task.rabbitmq.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 测试监听器
 *
 * @author ziyuan_deng
 * @create 2020-06-04 21:39
 */
@Component
public class CommonListener {

    private static final Logger log= LoggerFactory.getLogger(CommonListener.class);
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "${basic.info.mq.queue.name}",containerFactory = "singleListenerContainer")
    public void comsumerBasicMessage(User user){
     /*   String meg = new String(message);
        log.info("监听到的数据：{}",message);*/
        System.out.println("......");
        log.info("监听到的数据：{}",user);
    }
}
