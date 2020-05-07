package com.study.task.scheduledtask.enums;

import com.study.task.scheduledtask.constant.CodeConstant;

/**
 * @author ziyuan_deng
 * 定时器状态
 */
public enum SysJobStatusEnum {
    STOP(0,"暂停"),
    NOMAL(1,"正常")
    ;
    private Integer code;
    private String msg;

    private SysJobStatusEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }
}
