package com.study.task.login.utils;

import com.study.task.login.security.model.SelfUserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security工具类
 *
 * @author ziyuan_deng
 * @create 2020-05-17 0:50
 */
public class SecurityUtil {

    /**
     * 构造器私有化
     */
    private SecurityUtil(){}

    /**
     * 获取当前用户信息
     */
    public static SelfUserEntity getUserInfo(){
        SelfUserEntity userInfo = (SelfUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userInfo;
    }
    /**
     * 获取当前用户id
     */
    public static Long getUserId(){
        return getUserInfo().getUserId();
    }

    /**
     * 获取当前用户账号
     */
    public static String getUserName(){
        return getUserInfo().getUsername();
    }
}
