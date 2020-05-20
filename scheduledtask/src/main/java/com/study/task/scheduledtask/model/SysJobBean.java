package com.study.task.scheduledtask.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *  增加日期类型转换器，方式有四种：
 *
 *  第一种：在Bean实体字段或参数上增加@DateTimeFormat注解
 *
 *  第二种：在接收参数的的Controller中增加@InitBinder转化方法：
 *  @InitBinder
 *  protected  void initBinder(WebDataBinder binder) {
 *     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 *     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
 * }
 *
 *
 *
 * 定时任务信息
 */
public class SysJobBean {
    private String jobId;

    private String beanName;

    private String methodName;

    private String methodParams;

    private String cronExpression;

    private String remark;

    private Integer jobStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName == null ? null : beanName.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public String getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(String methodParams) {
        this.methodParams = methodParams == null ? null : methodParams.trim();
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}