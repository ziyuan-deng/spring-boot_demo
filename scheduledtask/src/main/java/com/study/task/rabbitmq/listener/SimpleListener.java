package com.study.task.rabbitmq.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息监听确认机制
 *
 * @author ziyuan_deng
 * @create 2020-06-05 23:23
 */
@Component("simpleListener")
public class SimpleListener implements ChannelAwareMessageListener {

    private static final Logger log= LoggerFactory.getLogger(SimpleListener.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String v = objectMapper.readValue(message.getBody(), String.class);
        log.info("接受到的信息：{}",v);
    }
}
