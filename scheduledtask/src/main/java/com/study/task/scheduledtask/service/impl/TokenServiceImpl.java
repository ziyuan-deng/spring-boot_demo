package com.study.task.scheduledtask.service.impl;

import com.study.task.scheduledtask.service.TokenService;
import com.study.task.scheduledtask.utils.RedisUtil;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * token处理逻辑
 *
 * @author ziyuan_deng
 * @create 2020-05-06 23:13
 */
@Service
public class TokenServiceImpl implements TokenService {
    private static Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public String createToken() {
        String token = UUID.randomUUID().toString();
        redisUtil.set(token,token,60L);
        return token;
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
             token = request.getParameter("token");
            if (StringUtils.isBlank(token)) {
                logger.error("TokenServiceImpl****请求重复提交：");
            }
        }
        if (!redisUtil.exists(token)) {
            logger.error("TokenServiceImpl****请求重复提交：");
            return;
        }
        redisUtil.remove(token);
    }
}
