package com.study.task.scheduledtask.enums;


import com.study.task.scheduledtask.constant.CodeConstant;

/**
 * 返回代码枚举类
 * 
 * @author wubin
 */
public enum CodeEnum {

    SUCCESS(CodeConstant.SUCCESS,"成功"),
    SAVE_FAILD(CodeConstant.SAVE_FAILD,"保存失败"),
    UPDATE_FAILD(CodeConstant.UPDATE_FAILD,"更新错误"),
    DELETE_FAILD(CodeConstant.DELETE_FAILD,"删除错误"),
    DELETE_DISABLED(CodeConstant.DELETE_FAILD,"存在关联数据，删除失败"),
    UNKNOWN_ERROR(CodeConstant.UNKNOWN_ERROR,"未知错误"),
    PARAMS_ERROR(CodeConstant.PARAMS_ERROR,"参数不合法!"),
    PARAMS_INCOMPLETENESS(CodeConstant.PARAMS_INCOMPLETENESS,"参数不完整"),
    QUERY_RESULT_ERROR(CodeConstant.QUERY_RESULT_ERROR,"查询结果异常"),
    NON_CONFORMITY_CONDITIONON(CodeConstant.NON_CONFORMITY_CONDITIONON,"不符合操作条件！"),
    DATABASE_OPERATION_ERROR(CodeConstant.OPERATION_SYSTEM_EXCEPTION,"数据库操作失败"),
    PERMISSION_UNLOGIN(CodeConstant.PERMISSION_UNLOGIN,"用户未登录"),
    LOGIN_USER_NO_EXIST(CodeConstant.LOGIN_USER_NO_EXIST,"用户不存在"),
    LOGIN_PASSWORD_ERROR(CodeConstant.LOGIN_PASSWORD_ERROR,"密码错误"),
    SERVER_ERROR(CodeConstant.UNKNOWN_ERROR,"服务异常")
    ;

    private Integer code;
    private String msg;

    private CodeEnum(Integer code, String msg){
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
