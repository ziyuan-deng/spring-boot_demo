package com.study.task.login.mapper;

import com.study.task.login.model.SysUserRoleEntity;
import com.study.task.login.model.SysUserRoleEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleEntityMapper {
    int countByExample(SysUserRoleEntityExample example);

    int deleteByExample(SysUserRoleEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUserRoleEntity record);

    int insertSelective(SysUserRoleEntity record);

    List<SysUserRoleEntity> selectByExample(SysUserRoleEntityExample example);

    SysUserRoleEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUserRoleEntity record, @Param("example") SysUserRoleEntityExample example);

    int updateByExample(@Param("record") SysUserRoleEntity record, @Param("example") SysUserRoleEntityExample example);

    int updateByPrimaryKeySelective(SysUserRoleEntity record);

    int updateByPrimaryKey(SysUserRoleEntity record);
}