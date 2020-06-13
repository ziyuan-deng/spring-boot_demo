package com.study.task.getpricetaskchain.service;

import com.study.task.getpricetaskchain.model.PriceDto;

/**
 * 去價抽象類
 *
 * @author ziyuan_deng
 * @create 2020-06-10 21:27
 */
public abstract class AbstractGetPriceTask {
    protected String name;// 抽象出审批人的姓名。
    // 下一个去加方案。
    protected AbstractGetPriceTask nextGetPriceTask;

    public  AbstractGetPriceTask(){}
    public AbstractGetPriceTask(String name) {
        this.name = name;
    }

    public AbstractGetPriceTask setNextApprover(AbstractGetPriceTask nextGetPriceTask) {
        this.nextGetPriceTask = nextGetPriceTask;
        // 返回下个审批人，链式编程。
        return this.nextGetPriceTask;
    }
    /**
     * 抽象审批方法由具体审批人子类实现
      */
    public abstract void getPrice(PriceDto priceDto);
}
