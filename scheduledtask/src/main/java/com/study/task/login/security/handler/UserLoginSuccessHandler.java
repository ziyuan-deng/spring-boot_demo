package com.study.task.login.security.handler;

import com.study.task.login.config.JwtConfig;
import com.study.task.login.security.model.SelfUserEntity;
import com.study.task.login.utils.JWTTokenUtil;
import com.study.task.login.utils.ResultUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功处理类
 *
 * @author ziyuan_deng
 * @create 2020-05-15 23:53
 */
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 登录成功返回结果
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 组装JWT
        SelfUserEntity selfUserEntity =  (SelfUserEntity) authentication.getPrincipal();
        String token = JWTTokenUtil.createToken(selfUserEntity);
        token = JwtConfig.tokenPrefix + token;
        // 封装返回参数
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("code","200");
        resultData.put("msg", "登录成功");
        resultData.put("token",token);
        ResultUtil.responseJson(response,resultData);
    }
}
