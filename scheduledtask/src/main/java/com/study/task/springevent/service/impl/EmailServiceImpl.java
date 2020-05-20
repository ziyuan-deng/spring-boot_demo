package com.study.task.springevent.service.impl;

import com.study.task.springevent.event.UserRegisterEvent;
import com.study.task.springevent.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * 发送邮件业务
 *
 * @author ziyuan_deng
 * @create 2020-05-11 23:27
 */
@Service
public class EmailServiceImpl implements EmailService, ApplicationListener<UserRegisterEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        logger.info("[onApplicationEvent][给用户({}) 发送邮件]", event.getUser().toString());
    }

}


