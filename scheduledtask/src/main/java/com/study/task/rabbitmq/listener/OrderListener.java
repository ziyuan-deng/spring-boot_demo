package com.study.task.rabbitmq.listener;

import com.study.task.rabbitmq.model.OrderRecord;
import com.study.task.rabbitmq.service.OrderService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单不支付监听处理
 *
 * @author ziyuan_deng
 * @create 2020-07-15 21:28
 */
@Component
public class OrderListener {

    private static final Logger LOGGER= LoggerFactory.getLogger(OrderListener.class);
    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "${user.order.dead.produce.queue.name}",containerFactory = "multiListenerContainer")
    private void comsumerOrderMessage(Integer orderId){
        LOGGER.info("订单id是："+ orderId);
        List<OrderRecord> orderRecords = orderService.findOrderById(orderId);
        OrderRecord orderRecord = null;
        int count = 0;
        if (CollectionUtils.isNotEmpty(orderRecords)) {
             orderRecord = orderRecords.get(0);
             orderRecord.setStatus(3);
             count = orderService.updateOrderState(orderRecord);
        }
        if (count>0) {
            LOGGER.info("用户"+orderRecord.getUserId()+"的订单id是："+ orderId + "已经取消了！");
        }
    }
}
