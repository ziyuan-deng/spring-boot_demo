package com.study.task.springevent.service.impl;

import com.study.task.springevent.event.UserRegisterEvent;
import com.study.task.springevent.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * 优惠券业务
 *
 * @author ziyuan_deng
 * @create 2020-05-11 23:36
 */
@Service
public class CouponServiceImpl implements CouponService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    @EventListener
    public void addCoupon(UserRegisterEvent event) {
        logger.info("[addCoupon][给用户({}) 发放优惠劵]", event.getUser().toString());
    }
}
