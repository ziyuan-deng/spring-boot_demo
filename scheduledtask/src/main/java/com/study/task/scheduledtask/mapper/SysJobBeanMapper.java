package com.study.task.scheduledtask.mapper;

import com.study.task.scheduledtask.model.SysJobBean;
import com.study.task.scheduledtask.model.SysJobBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysJobBeanMapper {
    int countByExample(SysJobBeanExample example);

    int deleteByExample(SysJobBeanExample example);

    int insert(SysJobBean record);

    int insertSelective(SysJobBean record);

    List<SysJobBean> selectByExample(SysJobBeanExample example);

    int updateByExampleSelective(@Param("record") SysJobBean record, @Param("example") SysJobBeanExample example);

    int updateByExample(@Param("record") SysJobBean record, @Param("example") SysJobBeanExample example);
}