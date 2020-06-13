package com.study.task.quartz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.task.quartz.mapper.JobMapper;
import com.study.task.quartz.model.QuartzJob;
import com.study.task.quartz.service.IJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobServiceImpl implements IJobService {

    private static final String TRIGGER_IDENTITY = "trigger";
    private static final String PARAM_NAME = "paramName";
    private static final String PARAM_VALUE = "paramValue";
    private static final String SCHEDULER_INSTANCE_NAME = "schedulerInstanceName";

    @Value("${spring.quartz.properties.org.quartz.scheduler.instanceName}")
    private String schedulerInstanceName;


    @Autowired
    private Scheduler scheduler;
    @Resource
    private JobMapper jobMapper;

    @Override
    public PageInfo listQuartzJob(String jobName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<QuartzJob> jobList = jobMapper.listJob(jobName);
        fillJobData(jobList);
        PageInfo pageInfo = new PageInfo(jobList);
        return pageInfo;
    }

    private void fillJobData(List<QuartzJob> jobList) {
        jobList.forEach(job -> {
            JobKey key = new JobKey(job.getJobName(), job.getJobGroup());
            try {
                JobDataMap jobDataMap = scheduler.getJobDetail(key).getJobDataMap();
                List<Map<String,Object>> jobDataParam = new ArrayList<>();
                jobDataMap.forEach((k,v) -> {
                    Map<String, Object> jobData = new LinkedHashMap<>(2);
                    jobData.put(PARAM_NAME,k);
                    jobData.put(PARAM_VALUE,v);
                    jobDataParam.add(jobData);
                });
                job.setJobDataParam(jobDataParam);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 保存并触发定时任务
     * @param quartz
     * @return
     * @throws Exception
     */
    @Override
    public int saveJob(QuartzJob quartz) throws Exception{
            //如果是修改  展示旧的 任务
            if(quartz.getOldJobGroup() != null && !"".equals(quartz.getOldJobGroup())){
                JobKey key = new JobKey(quartz.getOldJobName(),quartz.getOldJobGroup());
                scheduler.deleteJob(key);
            }

            //构建job信息
            Class cls = Class.forName(quartz.getJobClassName()) ;
            cls.newInstance();
            JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobName(),
                    quartz.getJobGroup())
                    .withDescription(quartz.getDescription()).build();
            putDataMap(job, quartz);

            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression().trim());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(TRIGGER_IDENTITY + quartz.getJobName(), quartz.getJobGroup())
                    .startNow().withSchedule(cronScheduleBuilder).build();
            //交由Scheduler安排触发
            scheduler.scheduleJob(job, trigger);

        return 1;
    }

    @Override
    public int triggerJob(String jobName, String jobGroup) throws Exception {
        JobKey key = new JobKey(jobName,jobGroup);
            scheduler.triggerJob(key);
        return 1;
    }

    @Override
    public int pauseJob(String jobName, String jobGroup) throws Exception {
        JobKey key = new JobKey(jobName,jobGroup);
        scheduler.pauseJob(key);
        return 1;
    }

    /**
     * 恢复任务
     * @param jobName
     * @param jobGroup
     * @return
     * @throws Exception
     */
    @Override
    public int resumeJob(String jobName, String jobGroup) throws Exception {

        JobKey key = new JobKey(jobName,jobGroup);
        scheduler.resumeJob(key);
        return 1;
    }

    /**
     * 移除定时任务
     * @param jobName
     * @param jobGroup
     * @return
     * @throws Exception
     */
    @Override
    public int removeJob(String jobName, String jobGroup) throws Exception {
        TriggerKey triggerKey = TriggerKey.triggerKey(TRIGGER_IDENTITY + jobName, jobGroup);
        // 停止触发器
        scheduler.pauseTrigger(triggerKey);
        // 移除触发器
        scheduler.unscheduleJob(triggerKey);
        // 删除任务
        scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
        System.out.println("removeJob:"+ JobKey.jobKey(jobName));
        return 1;
    }

    /**
     * 将scheduler instanceName存入jobDataMap，方便业务job中进行数据库操作
     * @param job
     * @param quartz
     */
    private void putDataMap(JobDetail job, QuartzJob quartz) {

        // 将scheduler instanceName存入jobDataMap，方便业务job中进行数据库操作
        JobDataMap jobDataMap = job.getJobDataMap();
        jobDataMap.put(SCHEDULER_INSTANCE_NAME, schedulerInstanceName);

        List<Map<String, Object>> jobDataParam = quartz.getJobDataParam();
        if (jobDataParam == null || jobDataParam.isEmpty()) {
            return;
        }
        jobDataParam.forEach(jobData -> jobDataMap.put(String.valueOf(jobData.get(PARAM_NAME)), jobData.get(PARAM_VALUE)));
    }
}