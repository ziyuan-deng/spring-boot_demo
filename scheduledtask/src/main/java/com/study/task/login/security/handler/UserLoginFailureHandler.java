package com.study.task.login.security.handler;

import com.study.task.login.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理类
 *
 * @author ziyuan_deng
 * @create 2020-05-15 23:43
 */
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

    private static final Logger log = LoggerFactory.getLogger(UserLoginFailureHandler.class);
    /**
     * 登录失败返回结果
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        //这些对于操作的处理类可以根据不同异常进行不同处理
        if (e instanceof UsernameNotFoundException) {
            log.info("【登录失败】"+e.getMessage());
            ResultUtil.responseJson(response,ResultUtil.resultCode(500,"用户名不存在"));
        }
        if (e instanceof LockedException){
            log.info("【登录失败】"+e.getMessage());
            ResultUtil.responseJson(response,ResultUtil.resultCode(500,"用户被冻结"));
        }
        if (e instanceof BadCredentialsException){
            log.info("【登录失败】"+e.getMessage());
            ResultUtil.responseJson(response,ResultUtil.resultCode(500,"用户名密码不正确"));
        }
        ResultUtil.responseJson(response,ResultUtil.resultCode(500,"登录失败"));
    }
}
