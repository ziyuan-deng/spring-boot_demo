package com.study.task.scheduledtask.controller;

import com.study.task.scheduledtask.annotation.ApiIdempotent;
import com.study.task.scheduledtask.common.Response;
import com.study.task.scheduledtask.enums.ResponseEnum;
import com.study.task.scheduledtask.model.SysJobBean;
import com.study.task.scheduledtask.service.TokenService;
import com.study.task.scheduledtask.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 检验重复提交
 *
 * @author ziyuan_deng
 * @create 2020-05-07 0:03
 */
@RestController
public class SellController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/createToken")
    public Response operatorTask(){
        try {
            String token =  tokenService.createToken();
            return ResponseUtil.success(token);
        } catch (Exception e) {
            return ResponseUtil.result(ResponseEnum.FAILED.getCode(), null, e.getMessage());
        }
    }
    @ApiIdempotent
    @PostMapping("/checkToken")
    public Response sellTask(HttpServletRequest request){
        try {
            String token =  "successful";
            String[] array = {"123","345","456"};
            List<String> list = Arrays.asList(array);
            return ResponseUtil.success(token);
        } catch (Exception e) {
            return ResponseUtil.result(ResponseEnum.FAILED.getCode(), null, e.getMessage());
        }
    }
}
