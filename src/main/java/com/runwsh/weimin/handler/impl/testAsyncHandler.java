package com.runwsh.weimin.handler.impl;

import com.runwsh.weimin.asyncEnum.AsyncEnum;
import com.runwsh.weimin.entity.LoanApplication;
import com.runwsh.weimin.handler.AsyncTaskHandler;
import com.runwsh.weimin.mapper.AsyncTaskMapper;
import com.runwsh.weimin.mapper.LoanApplicationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class testAsyncHandler implements AsyncTaskHandler {

    @Autowired
    LoanApplicationMapper loanApplicationMapper;

    @Override
    public String getTaskType() {
        log.info("測試新的查询订单信息異步任務");
        return AsyncEnum.TYPE_INSERT_STAT.getCode();
    }

    @Override
    public void execute(String taskParams) {
        log.info("執行插入異步任務，{}", taskParams);
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setUserId(1L);
        loanApplication.setAmount(BigDecimal.valueOf(20000));
        loanApplication.setTerm(12);
        loanApplication.setStatus("正常");
        loanApplication.setReason("啊啊是");
        loanApplicationMapper.insert(loanApplication);
    }
}
