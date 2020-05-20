package com.study.task.login.service;

import com.study.task.login.model.SysMenuEntity;
import com.study.task.login.model.SysRoleEntity;
import com.study.task.login.model.SysUserEntity;

import java.util.List;

public interface SysUserService {

    SysUserEntity loadUserByUsername(String s);

    List<SysRoleEntity> selectSysRoleByUserId(Long userId);

    List<SysMenuEntity> selectSysMenuByUserId(Long userId);

    List<SysUserEntity> list();
}
