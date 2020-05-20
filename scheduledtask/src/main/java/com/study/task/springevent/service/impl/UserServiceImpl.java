package com.study.task.springevent.service.impl;

import com.study.task.springevent.event.UserRegisterEvent;
import com.study.task.springevent.model.User;
import com.study.task.springevent.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * 用户处理逻辑
 *
 * @author ziyuan_deng
 * @create 2020-05-11 23:16
 */
@Service
public class UserServiceImpl implements UserService , ApplicationEventPublisherAware {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
             this.applicationEventPublisher = applicationEventPublisher;
    }


    @Override
    public void register(User user) {
        // ... 执行注册逻辑
        logger.info("[register][执行用户({}) 的注册逻辑]", user.toString());
        // <2> ... 发布
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this,user));
    }
}
