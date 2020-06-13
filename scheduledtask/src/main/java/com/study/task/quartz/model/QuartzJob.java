package com.study.task.quartz.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * qrtz_job_details对应的bean
 */
@Data
public class QuartzJob  implements Serializable {
	
	private String jobName;//任务名称
	private String jobGroup;//任务分组
	private String description;//任务描述
	private String jobClassName;//执行类
	private String cronExpression;//执行时间
	private String triggerName;//执行时间
	private String triggerState;//任务状态

	private String oldJobName;//任务名称 用于修改
	private String oldJobGroup;//任务分组 用于修改

	private List<Map<String, Object>> jobDataParam;
	
	public QuartzJob() {
		super();
	}
	public QuartzJob(String jobName, String jobGroup, String description, String jobClassName, String cronExpression, String triggerName) {
        super();
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.description = description;
        this.jobClassName = jobClassName;
        this.cronExpression = cronExpression;
        this.triggerName = triggerName;
    }
}
