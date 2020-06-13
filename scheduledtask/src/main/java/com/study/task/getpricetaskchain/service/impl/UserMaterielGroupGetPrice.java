package com.study.task.getpricetaskchain.service.impl;

import com.study.task.getpricetaskchain.model.PriceDto;
import com.study.task.getpricetaskchain.service.AbstractGetPriceTask;
import org.springframework.stereotype.Service;

/**
 * 用戶+物料組去價
 *
 * @author ziyuan_deng
 * @create 2020-06-10 21:39
 */
@Service("userMaterielGroupGetPrice")
public class UserMaterielGroupGetPrice extends AbstractGetPriceTask {

    public UserMaterielGroupGetPrice(){}

    public UserMaterielGroupGetPrice(String name) {
        super(name);
    }

    @Override
    public void getPrice(PriceDto priceDto) {
        System.out.println("執行到：UserMaterielGroupGetPrice");
        if (priceDto.getPrice()<=0) {
            this.nextGetPriceTask.getPrice(priceDto);
        }
    }
}
