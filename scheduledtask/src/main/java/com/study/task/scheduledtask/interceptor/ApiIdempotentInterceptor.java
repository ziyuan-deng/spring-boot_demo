package com.study.task.scheduledtask.interceptor;

import com.study.task.scheduledtask.annotation.ApiIdempotent;
import com.study.task.scheduledtask.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 重复提交校验拦截器
 *
 * @author ziyuan_deng
 * @create 2020-05-06 23:06
 */
public class ApiIdempotentInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod method = (HandlerMethod) handler;
        ApiIdempotent methodAnnotation = method.getMethodAnnotation(ApiIdempotent.class);
        if (methodAnnotation != null) {
            tokenService.checkToken(request);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
