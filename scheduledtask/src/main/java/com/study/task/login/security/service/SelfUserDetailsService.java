package com.study.task.login.security.service;

import com.study.task.login.model.SysUserEntity;
import com.study.task.login.security.model.SelfUserEntity;
import com.study.task.login.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SelfUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询用户信息
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public SelfUserEntity loadUserByUsername(String s) throws UsernameNotFoundException {
        //查询用户信息
        SysUserEntity userEntity =  sysUserService.loadUserByUsername(s);
        if (userEntity != null) {
            SelfUserEntity selfUserEntity = new SelfUserEntity();
            BeanUtils.copyProperties(userEntity,selfUserEntity);
            return selfUserEntity;
        }
        return null;
    }
}
