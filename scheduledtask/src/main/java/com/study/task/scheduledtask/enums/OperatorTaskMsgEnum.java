package com.study.task.scheduledtask.enums;

public enum OperatorTaskMsgEnum {

    STOP(0,"定时任务停止成功！"),
    START(1,"定时任务启动成功！")
    ;
    private Integer code;
    private String msg;

    private OperatorTaskMsgEnum(Integer code, String msg){
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
