package com.study.task.getpricetaskchain.service.impl;

import com.study.task.getpricetaskchain.model.PriceDto;
import com.study.task.getpricetaskchain.service.AbstractGetPriceTask;
import org.springframework.stereotype.Service;

/**
 * 物料+用戶去價
 *
 * @author ziyuan_deng
 * @create 2020-06-10 21:34
 */
@Service("userMaterielGetPrice")
public class UserMaterielGetPrice extends AbstractGetPriceTask {

    public UserMaterielGetPrice(){}

    public UserMaterielGetPrice(String name) {
        super(name);
    }

    @Override
    public void getPrice(PriceDto priceDto) {
        priceDto.setPrice(0);
        System.out.println("執行到：UserMaterielGetPrice");
        if (priceDto.getPrice()<=0) {
            this.nextGetPriceTask.getPrice(priceDto);
        }

    }
}
