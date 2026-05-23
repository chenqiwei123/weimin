package com.runwsh.weimin.handler.impl;

import com.runwsh.weimin.asyncEnum.AsyncEnum;
import com.runwsh.weimin.handler.AsyncTaskHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class testDeleteAsyncHandler implements AsyncTaskHandler {

    @Override
    public String getTaskType() {
        log.info("測試新的異步任務");
        return AsyncEnum.TYPE_DELETE_STAT.getCode();
    }

    @Override
    public void execute(String taskParams) {
        log.info("執行測試異步任務，{}", taskParams);

    }
}
