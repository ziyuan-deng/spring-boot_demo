package com.study.task.scheduledtask.controller;

import com.study.task.scheduledtask.common.Response;
import com.study.task.scheduledtask.enums.ResponseEnum;
import com.study.task.scheduledtask.model.SysJobBean;
import com.study.task.scheduledtask.service.SysJobService;
import com.study.task.scheduledtask.utils.RedisUtil;
import com.study.task.scheduledtask.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 定时任务CRUD
 *
 * @author ziyuan_deng
 * @create 2020-05-05 9:33
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private SysJobService jobService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/addTask")
    public Response addTask(@RequestBody SysJobBean jobBean){
        try {
            int result =  jobService.addTask(jobBean);
            return ResponseUtil.success(result);
        } catch (Exception e) {
            return ResponseUtil.result(ResponseEnum.FAILED.getCode(), null, e.getMessage());
        }
    }

    /**
     * 修改定时任务
     * @param jobBean
     * @return
     */

    @PostMapping("/editTask")
    public Response editTask(@RequestBody SysJobBean jobBean){
        try {
            int result =  jobService.editTask(jobBean);
            return ResponseUtil.success(result);
        } catch (Exception e) {
            return ResponseUtil.result(ResponseEnum.FAILED.getCode(), null, e.getMessage());
        }
    }

    /**
     * 删除定时任务
     * @param id
     * @return
     */
    @DeleteMapping("/deleteTask")
    public Response deleteTask(String id){
        try {
            int result =  jobService.deleteTask(id);
            return ResponseUtil.success(result);
        } catch (Exception e) {
            return ResponseUtil.result(ResponseEnum.FAILED.getCode(), null, e.getMessage());
        }
    }

    /**
     * 定时任务启动/停止状态切换
     * @param jobBean
     * @return
     */
    @PostMapping("/operatorTask")
    public Response operatorTask(@RequestBody SysJobBean jobBean){
        try {
            String operatorMsg =  jobService.operatorTask(jobBean);
            return ResponseUtil.success(operatorMsg);
        } catch (Exception e) {
            return ResponseUtil.result(ResponseEnum.FAILED.getCode(), null, e.getMessage());
        }
    }


}
