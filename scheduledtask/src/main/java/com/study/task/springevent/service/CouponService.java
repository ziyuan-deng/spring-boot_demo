package com.study.task.springevent.service;

import com.study.task.springevent.event.UserRegisterEvent;

public interface CouponService {

    public void addCoupon(UserRegisterEvent event);
}
