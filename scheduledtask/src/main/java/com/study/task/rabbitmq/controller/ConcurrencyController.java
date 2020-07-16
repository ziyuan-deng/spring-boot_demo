package com.study.task.rabbitmq.controller;

import com.study.task.rabbitmq.service.InitThreadService;
import com.study.task.scheduledtask.common.Response;
import com.study.task.scheduledtask.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟多线程抢单
 *
 * @author ziyuan_deng
 * @create 2020-06-05 11:52
 */
@RestController
@RequestMapping("/robbing")
public class ConcurrencyController {
    private static final Logger log= LoggerFactory.getLogger(ConcurrencyController.class);
    @Autowired
    private InitThreadService initThreadService;

    @GetMapping("/thread")
    public Response robbing(){
        try {
            initThreadService.generateMultiThread();
            return ResponseUtil.success("发送成功！");
        }catch (Exception e){
            log.error("请求失败");
            return ResponseUtil.exception();
        }
    }


}
