package com.study.task.scheduledtask.runner;

import com.study.task.scheduledtask.model.SysJobBean;
import com.study.task.scheduledtask.schedule.CronTaskRegistrar;
import com.study.task.scheduledtask.schedule.SchedulingRunnable;
import com.study.task.scheduledtask.service.SysJobService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 添加实现了CommandLineRunner接口的SysJobRunner类，当spring boot项目启动完成后，加载数据库里状态为正常的定时任务。
 *
 * @author ziyuan_deng
 * @create 2020-05-05 12:43
 */
@Service
public class SysJobRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SysJobRunner.class);

    @Autowired
    private SysJobService sysJobRepository;

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Override
    public void run(String... args) {
        // 初始加载数据库里状态为正常的定时任务
        List<SysJobBean> jobList = sysJobRepository.getSysJobListByStatus(1);
        if (CollectionUtils.isNotEmpty(jobList)) {
            for (SysJobBean job : jobList) {
                SchedulingRunnable task = new SchedulingRunnable(job.getBeanName(), job.getMethodName(), job.getMethodParams());
                cronTaskRegistrar.addCronTask(task, job.getCronExpression());
            }

            logger.info("定时任务已加载完毕...");
        }
    }
}
