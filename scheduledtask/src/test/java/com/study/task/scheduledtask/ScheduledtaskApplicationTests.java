package com.study.task.scheduledtask;

import com.study.task.scheduledtask.utils.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


public class ScheduledtaskApplicationTests {


    @Autowired
    private RedisUtil redisUtil;
    @Test
    public void testRedis(){
        System.out.println("测试redis");
        redisUtil.set("checkToken","112233");
        System.out.println("测试redis!!!!");
    }

}
