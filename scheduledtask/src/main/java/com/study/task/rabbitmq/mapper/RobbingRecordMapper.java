package com.study.task.rabbitmq.mapper;

import com.study.task.rabbitmq.model.RobbingRecord;
import com.study.task.rabbitmq.model.RobbingRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RobbingRecordMapper {
    int countByExample(RobbingRecordExample example);

    int deleteByExample(RobbingRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RobbingRecord record);

    int insertSelective(RobbingRecord record);

    List<RobbingRecord> selectByExample(RobbingRecordExample example);

    RobbingRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RobbingRecord record, @Param("example") RobbingRecordExample example);

    int updateByExample(@Param("record") RobbingRecord record, @Param("example") RobbingRecordExample example);

    int updateByPrimaryKeySelective(RobbingRecord record);

    int updateByPrimaryKey(RobbingRecord record);
}