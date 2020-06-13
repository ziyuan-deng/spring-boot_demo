package com.study.task.getpricetaskchain.controller;

import com.study.task.scheduledtask.common.Response;
import com.study.task.scheduledtask.utils.ResponseUtil;
import com.study.task.getpricetaskchain.model.PriceDto;
import com.study.task.getpricetaskchain.service.GetPriceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 責任鏈測試
 *
 * @author ziyuan_deng
 * @create 2020-06-10 21:25
 */
@RestController
@RequestMapping("/taskchain")
public class TaskChainController {

    @GetMapping("/price")
    public Response getPrice(){
        PriceDto priceDto = new PriceDto();
        /*AbstractGetPriceTask userMaterielGetPrice = new UserMaterielGetPrice("um");
        userMaterielGetPrice.setNextApprover(new UserGroupMaterielGetPrice("ugm"))
                            .setNextApprover(new UserMaterielGroupGetPrice("umg"))
                            .setNextApprover(new UserGroupMaterielGroupGextPrice("ugmg"));
        userMaterielGetPrice.getPrice(priceDto);*/
        Integer price = GetPriceUtils.getPrice(priceDto);
        return ResponseUtil.success(price);
    }

}
