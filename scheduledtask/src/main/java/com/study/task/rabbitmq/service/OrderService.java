package com.study.task.rabbitmq.service;

import com.study.task.rabbitmq.mapper.OrderRecordMapper;
import com.study.task.rabbitmq.model.OrderRecord;
import com.study.task.rabbitmq.model.OrderRecordExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单业务处理
 *
 * @author ziyuan_deng
 * @create 2020-07-15 17:17
 */
@Service
public class OrderService {
    @Autowired
    private OrderRecordMapper orderRecordMapper;

    public int saveOrderInfo(OrderRecord orderRecord){

        return orderRecordMapper.insert(orderRecord);
    }

    public int updateOrderState(OrderRecord orderRecord){
        return  orderRecordMapper.updateByPrimaryKeySelective(orderRecord);
    }

    public List<OrderRecord> findOrderById(Integer id){
        OrderRecordExample example = new OrderRecordExample();
        example.createCriteria().andIdEqualTo(id).andStatusEqualTo(1);
        return orderRecordMapper.selectByExample(example);
    }
}
