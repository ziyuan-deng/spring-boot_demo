package com.study.task.login.service.impl;

import com.study.task.login.mapper.SysMenuEntityMapper;
import com.study.task.login.model.SysMenuEntity;
import com.study.task.login.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单与权限
 *
 * @author ziyuan_deng
 * @create 2020-05-20 11:21
 */
@Service
public class SysMenuServiceImpl  implements SysMenuService {

    @Resource
    private SysMenuEntityMapper menuEntityMapper;
    @Override
    public List<SysMenuEntity> list() {
        return menuEntityMapper.selectByExample(null);
    }
}
