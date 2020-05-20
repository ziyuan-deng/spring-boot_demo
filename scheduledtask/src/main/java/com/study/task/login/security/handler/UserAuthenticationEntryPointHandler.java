package com.study.task.login.security.handler;

import com.study.task.login.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户未登录处理类
 *
 * @author ziyuan_deng
 * @create 2020-05-15 23:35
 */
@Component
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    private static final Logger log = LoggerFactory.getLogger(UserAuthenticationEntryPointHandler.class);
    /**
     *  用户未登录返回结果
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultUtil.resultCode(401,"未登录！");
    }
}
