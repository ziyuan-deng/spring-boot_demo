package com.study.task.scmbusinesschain.businesschain;

import com.study.task.scmbusinesschain.model.BillModel;

public interface IBusinessChainBatchExcutor {

    void loadProcessors(BillModel billModel);

    BillModel batchExcutor(BillModel billModel);
}
