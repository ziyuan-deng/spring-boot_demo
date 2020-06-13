package com.study.task.getpricetaskchain.service;

import com.study.task.distributedlock.utils.SpringContextHolder;
import com.study.task.getpricetaskchain.model.PriceDto;
import com.study.task.getpricetaskchain.service.impl.UserGroupMaterielGetPrice;
import com.study.task.getpricetaskchain.service.impl.UserGroupMaterielGroupGextPrice;
import com.study.task.getpricetaskchain.service.impl.UserMaterielGetPrice;
import com.study.task.getpricetaskchain.service.impl.UserMaterielGroupGetPrice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 去加工具類
 *
 * @author ziyuan_deng
 * @create 2020-06-10 22:45
 */
public class GetPriceUtils {

    public static Integer getPrice(PriceDto priceDto){

        List<Map<String,Object>> processList = new ArrayList<>();
        Map<String,Object> userMaterielMap = new HashMap<>();
        userMaterielMap.put("userMaterielGetPrice",new UserMaterielGetPrice());
        Map<String,Object> userGroupMaterielMap = new HashMap<>();
        userMaterielMap.put("userGroupMaterielGetPrice",new UserGroupMaterielGetPrice());
        Map<String,Object> userMaterielGroupMap = new HashMap<>();
        userMaterielMap.put("userMaterielGroupGetPrice",new UserMaterielGroupGetPrice());
        Map<String,Object> userGroupMaterielGroupMap = new HashMap<>();
        userMaterielMap.put("userGroupMaterielGroupGextPrice",new UserGroupMaterielGroupGextPrice());
        List<AbstractGetPriceTask> list = new ArrayList<>();
        list.add(new UserMaterielGetPrice());
        list.add(new UserGroupMaterielGetPrice());
        list.add(new UserMaterielGroupGetPrice());
        list.add(new UserGroupMaterielGroupGextPrice());
        AbstractGetPriceTask task = list.get(0);
        for (int i=0; i<list.size(); i++){
            if (i<list.size()-1){
                AbstractGetPriceTask priceTask = list.get(i);
                priceTask.setNextApprover(list.get(i+1));
            }
        }
       task.getPrice(priceDto);
        //AbstractGetPriceTask userMaterielGetPrice = SpringContextHolder.getBean("userMaterielGetPrice", UserMaterielGetPrice.class);
       /* userMaterielGetPrice.setNextApprover(SpringContextHolder.getBean("userGroupMaterielGetPrice", UserGroupMaterielGetPrice.class))
                            .setNextApprover(SpringContextHolder.getBean("userMaterielGroupGetPrice", UserMaterielGroupGetPrice.class))
                            .setNextApprover(SpringContextHolder.getBean("userGroupMaterielGroupGextPrice", UserGroupMaterielGroupGextPrice.class));*/
        /*userMaterielGetPrice.setNextApprover((AbstractGetPriceTask)SpringContextHolder.getBean("userGroupMaterielGetPrice"))
                            .setNextApprover((AbstractGetPriceTask)SpringContextHolder.getBean("userMaterielGroupGetPrice"))
                            .setNextApprover((AbstractGetPriceTask)SpringContextHolder.getBean("userGroupMaterielGroupGextPrice"));
        userMaterielGetPrice.getPrice(priceDto);*/
        return priceDto.getPrice();
    }
}
