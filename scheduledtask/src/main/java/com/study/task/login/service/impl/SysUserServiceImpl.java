package com.study.task.login.service.impl;

import com.study.task.login.mapper.SysUserEntityMapper;
import com.study.task.login.model.SysMenuEntity;
import com.study.task.login.model.SysRoleEntity;
import com.study.task.login.model.SysUserEntity;
import com.study.task.login.model.SysUserEntityExample;
import com.study.task.login.service.SysUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户业务处理
 *
 * @author ziyuan_deng
 * @create 2020-05-16 0:29
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserEntityMapper userEntityMapper;

    @Override
    public SysUserEntity loadUserByUsername(String s) {
        SysUserEntityExample example = new SysUserEntityExample();
        example.createCriteria().andUsernameEqualTo(s);
        List<SysUserEntity> sysUserEntities = userEntityMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(sysUserEntities)) {
            return sysUserEntities.get(0);
        }
        return null;
    }

    @Override
    public List<SysRoleEntity> selectSysRoleByUserId(Long userId) {
        List<SysRoleEntity> sysRoleEntities = userEntityMapper.selectSysRoleByUserId(userId);
        return sysRoleEntities;
    }

    @Override
    public List<SysMenuEntity> selectSysMenuByUserId(Long userId) {
        List<SysMenuEntity> sysMenuEntities = userEntityMapper.selectSysMenuByUserId(userId);
        return sysMenuEntities;
    }

    @Override
    public List<SysUserEntity> list() {
        return userEntityMapper.selectByExample(null);
    }
}
