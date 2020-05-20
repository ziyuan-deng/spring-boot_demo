package com.study.task.login.mapper;

import com.study.task.login.model.SysMenuEntity;
import com.study.task.login.model.SysMenuEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMenuEntityMapper {
    int countByExample(SysMenuEntityExample example);

    int deleteByExample(SysMenuEntityExample example);

    int deleteByPrimaryKey(Long menuId);

    int insert(SysMenuEntity record);

    int insertSelective(SysMenuEntity record);

    List<SysMenuEntity> selectByExample(SysMenuEntityExample example);

    SysMenuEntity selectByPrimaryKey(Long menuId);

    int updateByExampleSelective(@Param("record") SysMenuEntity record, @Param("example") SysMenuEntityExample example);

    int updateByExample(@Param("record") SysMenuEntity record, @Param("example") SysMenuEntityExample example);

    int updateByPrimaryKeySelective(SysMenuEntity record);

    int updateByPrimaryKey(SysMenuEntity record);
}