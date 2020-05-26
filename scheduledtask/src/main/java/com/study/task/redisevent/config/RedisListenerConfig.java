package com.study.task.redisevent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * redis的key过期监听事件配置类
 *
 * @author ziyuan_deng
 * @create 2020-05-26 10:06
 */
@Configuration
public class RedisListenerConfig {

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory){
        RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        return listenerContainer;
    }
}
