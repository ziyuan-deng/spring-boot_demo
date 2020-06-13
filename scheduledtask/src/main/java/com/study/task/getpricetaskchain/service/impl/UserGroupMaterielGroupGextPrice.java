package com.study.task.getpricetaskchain.service.impl;

import com.study.task.getpricetaskchain.model.PriceDto;
import com.study.task.getpricetaskchain.service.AbstractGetPriceTask;
import org.springframework.stereotype.Service;

/**
 * 物料組+用戶組去價
 *
 * @author ziyuan_deng
 * @create 2020-06-10 21:43
 */
@Service("userGroupMaterielGroupGextPrice")
public class UserGroupMaterielGroupGextPrice extends AbstractGetPriceTask {

    public UserGroupMaterielGroupGextPrice(){}

    public UserGroupMaterielGroupGextPrice(String name) {
        super(name);
    }

    @Override
    public void getPrice(PriceDto priceDto) {
       priceDto.setPrice(25);
    }
}
