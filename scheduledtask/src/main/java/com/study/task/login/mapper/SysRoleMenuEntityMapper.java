package com.study.task.login.mapper;

import com.study.task.login.model.SysRoleMenuEntity;
import com.study.task.login.model.SysRoleMenuEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMenuEntityMapper {
    int countByExample(SysRoleMenuEntityExample example);

    int deleteByExample(SysRoleMenuEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysRoleMenuEntity record);

    int insertSelective(SysRoleMenuEntity record);

    List<SysRoleMenuEntity> selectByExample(SysRoleMenuEntityExample example);

    SysRoleMenuEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysRoleMenuEntity record, @Param("example") SysRoleMenuEntityExample example);

    int updateByExample(@Param("record") SysRoleMenuEntity record, @Param("example") SysRoleMenuEntityExample example);

    int updateByPrimaryKeySelective(SysRoleMenuEntity record);

    int updateByPrimaryKey(SysRoleMenuEntity record);
}