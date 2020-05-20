package com.study.task.login.security.jwtfilter;

import com.alibaba.fastjson.JSONObject;
import com.study.task.login.config.JwtConfig;
import com.study.task.login.security.handler.UserAuthAccessDeniedHandler;
import com.study.task.login.security.model.SelfUserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JWT接口请求校验拦截器
 * 请求接口时会进入这里验证Token是否合法和过期
 *
 * @author ziyuan_deng
 * @create 2020-05-17 23:41
 */
public class JWTAuthenticationTokenFilter extends BasicAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(JWTAuthenticationTokenFilter.class);
    public JWTAuthenticationTokenFilter(AuthenticationManager manager){
        super(manager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取请求头中JWT的Token
        String tokenHeader  = request.getHeader(JwtConfig.tokenHeader);
        if (!StringUtils.isEmpty(tokenHeader) && tokenHeader .startsWith(JwtConfig.tokenPrefix)) {
            try {
                // 截取JWT前缀
                String token = tokenHeader.replace(JwtConfig.tokenPrefix, "");
                // 解析JWT
                Claims claims = Jwts.parser()
                        .setSigningKey(JwtConfig.secret)
                        .parseClaimsJws(token)
                        .getBody();
                // 获取用户名
                String username = claims.getSubject();
                String userId=claims.getId();
                if(!StringUtils.isEmpty(username)&&!StringUtils.isEmpty(userId)) {
                    // 获取角色
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    Object object =  claims.get("authorities");
                    Object t = JSONObject.toJSON(object);
                    String authority = t.toString();
                    //String authority = claims.get("authorities").toString();
                    if(!StringUtils.isEmpty(authority)){
                        List<Map<String,String>> authorityMap = JSONObject.parseObject(authority, List.class);
                        for(Map<String,String> role : authorityMap){
                            if(!StringUtils.isEmpty(role)) {
                                authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                            }
                        }
                    }
                    //组装参数
                    SelfUserEntity selfUserEntity = new SelfUserEntity();
                    selfUserEntity.setUsername(claims.getSubject());
                    selfUserEntity.setUserId(Long.parseLong(claims.getId()));
                    selfUserEntity.setAuthorities(authorities);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(selfUserEntity, userId, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e){
                log.info("Token过期");
            } catch (Exception e) {
                e.printStackTrace();
                log.info("Token无效");
            }
        }
        super.doFilterInternal(request, response, chain);
    }
}
