package com.study.task.quartz.mapper;

import com.study.task.quartz.model.QuartzJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 查询所有定时任务
 */
public interface JobMapper {

    List<QuartzJob> listJob(@Param("jobName") String jobName);
}
