package com.study.task.scmbusinesschain.processor;

import com.study.task.scmbusinesschain.model.BillModel;

/**
 * 業務鏈業務處理器抽象類
 *
 * @author ziyuan_deng
 * @create 2020-06-11 10:44
 */
public abstract class AbstractBusinessProcessor implements IbusinessProcessor {

    @Override
    public void execute(BillModel todoBillInfo) {
        boolean check = this.check(todoBillInfo);
        this.doProcessing(todoBillInfo);
    }

    /**
     * 業務處理器校驗接口
     * @param todoBillInfo
     * @return
     */
    public abstract boolean check(BillModel todoBillInfo);

    /**
     * 業務處理器的業務處理接口
     * @param todoBillInfo
     */
    public abstract  void  doProcessing(BillModel todoBillInfo);



}
