package com.study.task.login.service.impl;

import com.study.task.login.mapper.SysRoleEntityMapper;
import com.study.task.login.model.SysRoleEntity;
import com.study.task.login.model.SysRoleMenuEntity;
import com.study.task.login.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色获取
 *
 * @author ziyuan_deng
 * @create 2020-05-20 11:23
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleEntityMapper roleEntityMapper;
    @Override
    public List<SysRoleEntity> list() {
        return roleEntityMapper.selectByExample(null);
    }
}
