package com.study.task.scmbusinesschain.businesschain;

import com.study.task.distributedlock.utils.SpringContextHolder;
import com.study.task.scmbusinesschain.model.BillModel;
import com.study.task.scmbusinesschain.processor.AbstractBusinessProcessor;
import com.study.task.scmbusinesschain.processor.IbusinessProcessor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 調用業務鏈的支持類
 *
 * @author ziyuan_deng
 * @create 2020-06-11 11:45
 */
@Service
public class IBusinessChainSupport implements IBusinessChainBatchExcutor {

    List<AbstractBusinessProcessor> processors = new ArrayList<>();

    @Override
    public void loadProcessors(BillModel billModel) {
        //todo 通過查詢數據庫獲取業務鏈的所有業務處理器

        //然後從IOC容器中獲取處理器放入鏈表中
        processors.add((AbstractBusinessProcessor)SpringContextHolder.getBean("writeBackSaleOrderProcessor"));
        processors.add((AbstractBusinessProcessor)SpringContextHolder.getBean("writeBackSaleCreditProcessor"));
        processors.add((AbstractBusinessProcessor)SpringContextHolder.getBean("writeBackTransferBillProcessor"));
    }

    /**
     * 進入業務鏈實現接口
     * @param billModel
     * @return
     */
    @Override
    public BillModel batchExcutor(BillModel billModel) {
        BillModel billReult = null;
        this.loadProcessors(billModel);
        for (AbstractBusinessProcessor processor : processors) {
            processor.check(billModel);
        }
        if (billModel.isChechResult()) {
            for (AbstractBusinessProcessor processor : processors) {
                processor.doProcessing(billModel);
            }
        }
        billReult = billModel;
        return billReult;
    }


}
