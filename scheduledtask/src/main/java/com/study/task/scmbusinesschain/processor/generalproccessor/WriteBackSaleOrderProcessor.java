package com.study.task.scmbusinesschain.processor.generalproccessor;

import com.study.task.scmbusinesschain.model.BillModel;
import com.study.task.scmbusinesschain.processor.AbstractBusinessProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 反寫銷售訂單處理器
 *
 * @author ziyuan_deng
 * @create 2020-06-11 11:16
 */
@Component("writeBackSaleOrderProcessor")
public class WriteBackSaleOrderProcessor extends AbstractBusinessProcessor {
    private static final Logger logger = LoggerFactory.getLogger(WriteBackSaleOrderProcessor.class);
    @Override
    public boolean check(BillModel todoBillInfo) {
        return true;
    }

    @Override
    public void doProcessing(BillModel todoBillInfo) {
        logger.info("處理到反寫銷售訂單處理器：WriteBackSaleOrderProcessor");
    }
}
