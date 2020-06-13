package com.study.task.quartz.controller;

import com.github.pagehelper.PageInfo;
import com.study.task.quartz.model.QuartzJob;
import com.study.task.quartz.service.IJobService;
import com.study.task.scheduledtask.common.Response;
import com.study.task.scheduledtask.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {
	private final static Logger LOGGER = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private IJobService jobService;
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/add")
	public Response save(QuartzJob quartz){
		LOGGER.info("新增任务");
		try {
			int count = jobService.saveJob(quartz);
			if (count==1) {
				return ResponseUtil.success(count);
			}else {
				return  ResponseUtil.exception();
			}
		}catch (Exception e){
			LOGGER.error("新增任务出错：",e.getMessage());
			e.printStackTrace();
			return  ResponseUtil.exception();
		}

	}
	@PostMapping("/list")
	public Response list(String jobName,Integer pageNo,Integer pageSize){
		LOGGER.info("任务列表");
		try {
			PageInfo pageInfo = jobService.listQuartzJob(jobName, pageNo, pageSize);
			return ResponseUtil.success(pageInfo);
		}catch (Exception e){
			LOGGER.error("任务列表出错：",e.getMessage());
			return  ResponseUtil.exception();
		}
	}

	@PostMapping("/trigger")
	public  Response trigger(String jobName, String jobGroup) {
		LOGGER.info("触发任务");
		try {
			int count = jobService.triggerJob(jobName, jobGroup);
			return ResponseUtil.success(count);
		}catch (Exception e){
			LOGGER.error("触发任务出错：",e.getMessage());
			return  ResponseUtil.exception();
		}
	}

	@PostMapping("/pause")
	public  Response pause(String jobName, String jobGroup) {
		LOGGER.info("停止任务");
		try {
			int count = jobService.pauseJob(jobName, jobGroup);
			return ResponseUtil.success(count);
		}catch (Exception e){
			LOGGER.error("停止任务出错：",e.getMessage());
			return  ResponseUtil.exception();
		}
	}

	@PostMapping("/resume")
	public  Response resume(String jobName, String jobGroup) {
		LOGGER.info("恢复任务");
		try {
			int count = jobService.resumeJob(jobName, jobGroup);
			return ResponseUtil.success(count);
		}catch (Exception e){
			LOGGER.error("恢复任务出错：",e.getMessage());
			return  ResponseUtil.exception();
		}
	}

	@PostMapping("/remove")
	public  Response remove(String jobName, String jobGroup) {
		LOGGER.info("移除任务");
		try {
			int count = jobService.removeJob(jobName, jobGroup);
			return ResponseUtil.success(count);
		}catch (Exception e){
			LOGGER.error("移除任务出错：",e.getMessage());
			return  ResponseUtil.exception();
		}
	}
}
