package com.study.task.scmbusinesschain.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 業務鏈單據數據
 *
 * @author ziyuan_deng
 * @create 2020-06-11 10:41
 */
@Data
public class BillModel implements Serializable {

    private String billId;
    //反寫銷售訂單
    private Integer saleOrderWriteBackCount;

    private String materialId;

    private int saleOrderState;

    //校驗業務鏈的結果
    private boolean chechResult = true;

    //反寫調撥訂單
    private Integer tansferBillWriteBackCount;
    //反寫銷售信用
    private Integer saleCreditWriteBackCount;
    //異常信息匯總類
    private Map<String,Object> errersInfo = new HashMap<>();

}
