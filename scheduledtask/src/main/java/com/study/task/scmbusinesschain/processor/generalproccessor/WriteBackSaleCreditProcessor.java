package com.study.task.scmbusinesschain.processor.generalproccessor;

import com.study.task.scmbusinesschain.model.BillModel;
import com.study.task.scmbusinesschain.processor.AbstractBusinessProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 反寫銷售信用處理器
 *
 * @author ziyuan_deng
 * @create 2020-06-11 11:38
 */
@Component("writeBackSaleCreditProcessor")
public class WriteBackSaleCreditProcessor extends AbstractBusinessProcessor {

    private static final Logger logger = LoggerFactory.getLogger(WriteBackSaleCreditProcessor.class);
    @Override
    public boolean check(BillModel todoBillInfo) {
        List<String> errorList = new ArrayList<>();
        errorList.add("單價已經關閉！");
        todoBillInfo.getErrersInfo().put("writeBackSaleCreditProcessor",errorList);
        todoBillInfo.setChechResult(false);
        return false;
    }

    @Override
    public void doProcessing(BillModel todoBillInfo) {
        logger.info("處理到反寫銷售信用處理器：WriteBackSaleCreditProcessor");
    }
}
