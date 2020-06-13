package com.study.task.getpricetaskchain.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 去價條件數據類
 *
 * @author ziyuan_deng
 * @create 2020-06-10 21:50
 */
@Data
public class PriceDto implements Serializable {

    private String userId;

    private String materialId;

    private String userGroupId;

    private String materialGroupId;

    private Integer price;
}
