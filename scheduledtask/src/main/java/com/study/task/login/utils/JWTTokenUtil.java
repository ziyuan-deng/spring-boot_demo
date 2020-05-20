package com.study.task.login.utils;

import com.study.task.login.config.JwtConfig;
import com.study.task.login.security.model.SelfUserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * JWT工具类
 *
 * @author ziyuan_deng
 * @create 2020-05-15 22:34
 */
@Slf4j
public class JWTTokenUtil {

    public static String createToken(SelfUserEntity userEntity){

        String token = Jwts.builder()
               .setId(userEntity.getUserId().toString())
               .setSubject(userEntity.getUsername())
               .setIssuedAt(new Date())
               .setIssuer("sans")
               .claim("authorities",userEntity.getAuthorities())
               .setExpiration(new Date(System.currentTimeMillis()+ JwtConfig.expiration))
                // 签名算法和密钥
               .signWith(SignatureAlgorithm.HS512, JwtConfig.secret)
               .compact();
        return token;
    }
}
