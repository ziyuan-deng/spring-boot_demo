package com.study.task.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 例子：定义一个执行输出语句的任务
 * @date: 2018-11-05 17:31
 */
@Component
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Hello执行：" + LocalDateTime.now());
    }
}
