package com.study.task.login.mapper;

import com.study.task.login.model.SysMenuEntity;
import com.study.task.login.model.SysRoleEntity;
import com.study.task.login.model.SysUserEntity;
import com.study.task.login.model.SysUserEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserEntityMapper {
    int countByExample(SysUserEntityExample example);

    int deleteByExample(SysUserEntityExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(SysUserEntity record);

    int insertSelective(SysUserEntity record);

    List<SysUserEntity> selectByExample(SysUserEntityExample example);

    SysUserEntity selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") SysUserEntity record, @Param("example") SysUserEntityExample example);

    int updateByExample(@Param("record") SysUserEntity record, @Param("example") SysUserEntityExample example);

    int updateByPrimaryKeySelective(SysUserEntity record);

    int updateByPrimaryKey(SysUserEntity record);

    List<SysRoleEntity> selectSysRoleByUserId(Long userId);

    List<SysMenuEntity> selectSysMenuByUserId(Long userId);
}