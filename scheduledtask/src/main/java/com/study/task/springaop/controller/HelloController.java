package com.study.task.springaop.controller;

import com.study.task.springaop.annotation.EagleEye;
import com.study.task.springaop.aop.EagleEyeAop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo
 *
 * @author ziyuan_deng
 * @create 2020-05-27 16:02
 */
@RestController
@RequestMapping("/aop")
public class HelloController {

    protected static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @EagleEye(desc = "调用test接口")
    @GetMapping("/test")
    public Object test(String name){
        logger.info("jinlai");
        return  name;
    }

}
