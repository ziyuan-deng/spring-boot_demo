package com.study.task.scheduledtask.job;

import org.springframework.stereotype.Component;

/**
 * 添加定时任务示例类
 *
 * @author ziyuan_deng
 * @create 2020-05-05 12:34
 */
@Component("demoTask")
public class DemoTask {
    public void taskWithParams(String params) {
        System.out.println("执行有参示例任务：" + params);
    }

    public void taskNoParams() {
        System.out.println("执行无参示例任务");
    }
}
