package com.study.task.scheduledtask.service;

import com.study.task.scheduledtask.model.SysJobBean;

import java.util.List;

public interface SysJobService {
    SysJobBean getTaskInfo(String id);

    List<SysJobBean> getSysJobListByStatus(int status);

    int addTask(SysJobBean jobBean);

    int editTask(SysJobBean jobBean);

    int deleteTask(String id);

    String operatorTask(SysJobBean jobBean);
}
