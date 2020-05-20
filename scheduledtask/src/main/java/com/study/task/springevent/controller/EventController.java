package com.study.task.springevent.controller;

import com.study.task.springevent.model.User;
import com.study.task.springevent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户注册事件请求
 *
 * @author ziyuan_deng
 * @create 2020-05-11 23:40
 */
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.register(user);
        return "success";
    }
}
