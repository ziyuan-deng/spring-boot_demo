package com.study.task.login.security.handler;

import com.study.task.login.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 暂无权限处理类
 *
 * @author ziyuan_deng
 * @create 2020-05-15 23:29
 */
@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger log = LoggerFactory.getLogger(UserAuthAccessDeniedHandler.class);
    /**
     * 暂无权限返回结果
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        log.error("未授权");
       // ResultUtil.resultCode(403,"未授权");
        ResultUtil.responseJson(response,ResultUtil.resultCode(403,"未授权"));
    }
}
