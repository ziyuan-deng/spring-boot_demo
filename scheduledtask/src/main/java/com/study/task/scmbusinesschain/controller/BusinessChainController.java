package com.study.task.scmbusinesschain.controller;

import com.study.task.scheduledtask.common.Response;
import com.study.task.scheduledtask.utils.ResponseUtil;
import com.study.task.scmbusinesschain.businesschain.IBusinessChainSupport;
import com.study.task.scmbusinesschain.model.BillModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ziyuan_deng
 * @create 2020-06-11 12:08
 */
@RestController
@RequestMapping("/businesschain")
public class BusinessChainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessChainController.class);

    @Autowired
    private IBusinessChainSupport businessChainSupport;
    @GetMapping("/audit")
    public Response audit(){
        BillModel billModel = new BillModel();
        businessChainSupport.batchExcutor(billModel);
        if (billModel.isChechResult()) {
            return ResponseUtil.success(billModel);
        }else {
            return ResponseUtil.retBatchErrorInfo(billModel.getErrersInfo(),"審核單價業務校驗未通過");
        }
    }
}
