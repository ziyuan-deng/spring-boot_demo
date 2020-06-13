package com.study.task.scmbusinesschain.processor.generalproccessor;

import com.study.task.scmbusinesschain.model.BillModel;
import com.study.task.scmbusinesschain.processor.AbstractBusinessProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 反寫調撥訂單
 *
 * @author ziyuan_deng
 * @create 2020-06-11 11:33
 */
@Component("writeBackTransferBillProcessor")
public class WriteBackTransferBillProcessor extends AbstractBusinessProcessor {
    private static final Logger logger = LoggerFactory.getLogger(WriteBackTransferBillProcessor.class);
    @Override
    public boolean check(BillModel todoBillInfo) {
        return true;
    }

    @Override
    public void doProcessing(BillModel todoBillInfo) {
        logger.info("處理到反寫調撥訂單處理器：WriteBackTransferBillProcessor");
    }
}
