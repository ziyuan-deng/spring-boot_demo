package com.study.task.getpricetaskchain.service.impl;

import com.study.task.getpricetaskchain.model.PriceDto;
import com.study.task.getpricetaskchain.service.AbstractGetPriceTask;
import org.springframework.stereotype.Service;

/**
 * 用戶組+物料
 *
 * @author ziyuan_deng
 * @create 2020-06-10 21:37
 */
@Service("userGroupMaterielGetPrice")
public class UserGroupMaterielGetPrice extends AbstractGetPriceTask {

    public UserGroupMaterielGetPrice(){}

    public UserGroupMaterielGetPrice(String name) {
        super(name);
    }

    @Override
    public void getPrice(PriceDto priceDto) {

        System.out.println("執行到：UserGroupMaterielGetPrice");
        if (priceDto.getPrice()<=0) {
            this.nextGetPriceTask.getPrice(priceDto);
        }
    }
}
