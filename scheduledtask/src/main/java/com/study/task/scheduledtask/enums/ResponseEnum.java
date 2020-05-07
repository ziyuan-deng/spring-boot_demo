package com.study.task.scheduledtask.enums;

/**
 * 响应枚举
 **/
public enum ResponseEnum {

    SUCCESS("成功", 0),
    NOT_EXIST("查询结果不存在", 201),
    EXIST("已存在，请勿重复添加", 202),
    FAILED("服务器繁忙", 500),
    TOKEN_EXCEPTION("token异常", 601),
    CHECKPASSWORD_NOT_FIT("密码与确认密码不相符！", 602),
    EXPIRETIME_SET_NOT_REASONABLE("过期日期是今天的日期之前，设置不合理！", 603),
    PARAMS_ERROR("参数异常", -3);
    // 成员变量
    private String msg;
    private int code;
    // 构造方法
    private ResponseEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
    // 普通方法
    public static String getMsg(int code) {
        for (ResponseEnum re : ResponseEnum.values()) {
            if (re.getCode() == code) {
                return re.getMsg();
            }
        }
        return null;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }}
