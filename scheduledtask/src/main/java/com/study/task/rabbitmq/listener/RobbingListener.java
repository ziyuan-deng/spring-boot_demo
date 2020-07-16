package com.study.task.rabbitmq.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.task.rabbitmq.mapper.ProductMapper;
import com.study.task.rabbitmq.mapper.RobbingRecordMapper;
import com.study.task.rabbitmq.model.Product;
import com.study.task.rabbitmq.model.ProductExample;
import com.study.task.rabbitmq.model.RobbingRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 监听抢单消息，并消费
 *
 * @author ziyuan_deng
 * @create 2020-06-05 13:18
 */
@Component
public class RobbingListener {
    private static final Logger log= LoggerFactory.getLogger(RobbingListener.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private RobbingRecordMapper robbingRecordMapper;

    private static final String PRODUCT_NO = "product_10010";

    /**
     * 监听发布的抢单信息，对抢单消息进行消费
     * @param mobile
     */
    @RabbitListener(queues = "${product.robbing.mq.queue.name}",containerFactory = "singleListenerContainer")
    public void robbingOrderInfo(Integer mobile){

        try {

            int result = productMapper.updateProductTotal(PRODUCT_NO);
            //总数大于零的情况，才抢单成功
            if (result>0){
                RobbingRecord robbingRecord = new RobbingRecord();
                robbingRecord.setMobile(mobile.toString());
                robbingRecord.setProductId(1);
                robbingRecord.setRobbingTime(new Date());
                robbingRecord.setUpdateTime(new Date());
                robbingRecordMapper.insert(robbingRecord);
                log.info("抢单成功，手机号为：{}",mobile);
            }
        }catch (Exception e){
            log.error("消费签单消息异常：",e);
        }
    }

}
