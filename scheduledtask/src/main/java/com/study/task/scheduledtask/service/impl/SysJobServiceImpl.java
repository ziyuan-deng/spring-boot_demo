package com.study.task.scheduledtask.service.impl;

import com.study.task.scheduledtask.enums.OperatorTaskMsgEnum;
import com.study.task.scheduledtask.enums.SysJobStatusEnum;
import com.study.task.scheduledtask.mapper.SysJobBeanMapper;
import com.study.task.scheduledtask.model.SysJobBean;
import com.study.task.scheduledtask.model.SysJobBeanExample;
import com.study.task.scheduledtask.schedule.CronTaskRegistrar;
import com.study.task.scheduledtask.schedule.SchedulingRunnable;
import com.study.task.scheduledtask.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务管理
 *
 * @author ziyuan_deng
 * @create 2020-05-05 10:51
 */
@Service
public class SysJobServiceImpl implements SysJobService {

    @Resource
    private SysJobBeanMapper jobBeanMapper;
    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;
    @Override
    public SysJobBean getTaskInfo(String id) {
        SysJobBeanExample example = new SysJobBeanExample();
        example.createCriteria().andJobIdEqualTo(id);
        List<SysJobBean> sysJobBeans = jobBeanMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(sysJobBeans)) {
            return null;
        }
        return sysJobBeans.get(0);
    }

    /**
     * 获取所有正常状态下的定时任务
     * @param status
     * @return
     */
    @Override
    public List<SysJobBean> getSysJobListByStatus(int status) {
        SysJobBeanExample example = new SysJobBeanExample();
        example.createCriteria().andJobStatusEqualTo(status);
        List<SysJobBean> sysJobBeans = jobBeanMapper.selectByExample(example);
        return sysJobBeans;
    }

    /**
     * 添加定时任务
     * @param jobBean
     * @return
     */
    @Override
    public int addTask(SysJobBean jobBean) {
        int result = jobBeanMapper.insert(jobBean);
        if (result >0){
            if (jobBean.getJobStatus().equals(SysJobStatusEnum.NOMAL.getCode())) {
                SchedulingRunnable task = new SchedulingRunnable(jobBean.getBeanName(), jobBean.getMethodName(), jobBean.getMethodParams());
                cronTaskRegistrar.addCronTask(task, jobBean.getCronExpression());
            }
            return result;
        }else{
            return 0;
        }
    }

    /**
     * 修改定时任务，先移除原来的任务，再启动新任务
     * @param jobBean
     * @return
     */
    @Override
    public int editTask(SysJobBean jobBean) {
        SysJobBeanExample existExample = new SysJobBeanExample();
        existExample.createCriteria().andJobIdEqualTo(jobBean.getJobId());
        List<SysJobBean> sysJobBeans = jobBeanMapper.selectByExample(existExample);
        SysJobBean existedSysJob = sysJobBeans.get(0);
        SysJobBeanExample example = new SysJobBeanExample();
        example.createCriteria().andJobIdEqualTo(jobBean.getJobId());
        int result = jobBeanMapper.updateByExampleSelective(jobBean,example);
        if (result>0) {
            //先移除再添加
            if (existedSysJob.getJobStatus().equals(SysJobStatusEnum.NOMAL.getCode())) {
                SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
                cronTaskRegistrar.removeCronTask(task);
            }

            if (jobBean.getJobStatus().equals(SysJobStatusEnum.NOMAL.getCode())) {
                SchedulingRunnable task = new SchedulingRunnable(jobBean.getBeanName(), jobBean.getMethodName(), jobBean.getMethodParams());
                cronTaskRegistrar.addCronTask(task, jobBean.getCronExpression());
            }
            return result;
        }
        return 0;
    }

    @Override
    public int deleteTask(String id) {
        SysJobBeanExample existExample = new SysJobBeanExample();
        existExample.createCriteria().andJobIdEqualTo(id);
        List<SysJobBean> sysJobBeans = jobBeanMapper.selectByExample(existExample);
        SysJobBean existedSysJob = sysJobBeans.get(0);
        int result = jobBeanMapper.deleteByExample(existExample);
        if (result>0) {
            //移除
            if (existedSysJob.getJobStatus().equals(SysJobStatusEnum.NOMAL.getCode())) {
                SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
                cronTaskRegistrar.removeCronTask(task);
            }
            return result;
        }
        return 0;
    }

    @Override
    public String operatorTask(SysJobBean jobBean) {
        SysJobBeanExample existExample = new SysJobBeanExample();
        existExample.createCriteria().andJobIdEqualTo(jobBean.getJobId());
        List<SysJobBean> sysJobBeans = jobBeanMapper.selectByExample(existExample);
        SysJobBean existedSysJob = sysJobBeans.get(0);
        if (jobBean.getJobStatus().equals(SysJobStatusEnum.STOP.getCode())) {
            SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
            cronTaskRegistrar.removeCronTask(task);
            return OperatorTaskMsgEnum.STOP.getMsg();
        } else {
            SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
            cronTaskRegistrar.addCronTask(task, existedSysJob.getCronExpression());
            return OperatorTaskMsgEnum.START.getMsg();
        }
    }
}
