package com.study.task.quartz.service;

import com.github.pagehelper.PageInfo;
import com.study.task.quartz.model.QuartzJob;

public interface IJobService {

    PageInfo listQuartzJob(String jobName, Integer pageNo, Integer pageSize);

    /**
     * 新增job
     * @param quartz
     * @return
     */
    int saveJob(QuartzJob quartz) throws Exception;

    /**
     * 触发job
     * @param jobName
     * @param jobGroup
     * @return
     */
    int triggerJob(String jobName, String jobGroup) throws Exception;

    /**
     * 暂停job
     * @param jobName
     * @param jobGroup
     * @return
     */
    int pauseJob(String jobName, String jobGroup) throws Exception;

    /**
     * 恢复job
     * @param jobName
     * @param jobGroup
     * @return
     */
    int resumeJob(String jobName, String jobGroup) throws Exception;

    /**
     * 移除job
     * @param jobName
     * @param jobGroup
     * @return
     */
    int removeJob(String jobName, String jobGroup) throws Exception;
}