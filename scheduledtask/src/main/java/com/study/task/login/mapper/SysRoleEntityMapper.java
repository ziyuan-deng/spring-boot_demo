package com.study.task.login.mapper;

import com.study.task.login.model.SysRoleEntity;
import com.study.task.login.model.SysRoleEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleEntityMapper {
    int countByExample(SysRoleEntityExample example);

    int deleteByExample(SysRoleEntityExample example);

    int deleteByPrimaryKey(Long roleId);

    int insert(SysRoleEntity record);

    int insertSelective(SysRoleEntity record);

    List<SysRoleEntity> selectByExample(SysRoleEntityExample example);

    SysRoleEntity selectByPrimaryKey(Long roleId);

    int updateByExampleSelective(@Param("record") SysRoleEntity record, @Param("example") SysRoleEntityExample example);

    int updateByExample(@Param("record") SysRoleEntity record, @Param("example") SysRoleEntityExample example);

    int updateByPrimaryKeySelective(SysRoleEntity record);

    int updateByPrimaryKey(SysRoleEntity record);
}