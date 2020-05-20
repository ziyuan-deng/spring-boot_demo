package com.study.task.springevent.event;

import com.study.task.springevent.model.User;
import org.springframework.context.ApplicationEvent;

/**
 * 用户注册监听事件
 *
 * @author ziyuan_deng
 * @create 2020-05-11 23:03
 */
public class UserRegisterEvent extends ApplicationEvent {

    private User user;
    
    public UserRegisterEvent(Object source) {
        super(source);
    }


    public UserRegisterEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
