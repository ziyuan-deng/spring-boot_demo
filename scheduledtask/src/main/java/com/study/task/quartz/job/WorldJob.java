package com.study.task.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

/**
 * 测试定时任务
 *
 * @author ziyuan_deng
 * @create 2020-06-13 22:54
 */
public class WorldJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("world执行：" + LocalDateTime.now());
    }
}
