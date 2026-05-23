package com.runwsh.weimin.scheduler;

import com.runwsh.weimin.asyncEnum.AsyncEnum;
import com.runwsh.weimin.entity.AsyncTask;
import com.runwsh.weimin.service.AsyncTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final AsyncTaskService asyncTaskService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void insertTask() {
        log.info("开始执行插入异步任务");
        
        AsyncTask task = asyncTaskService.createTask(
                "插入订单数据",
                AsyncEnum.TYPE_INSERT_STAT.getCode(),
                ""
        );
        
        asyncTaskService.executeTask(task.getId());
        log.info("Daily statistics task scheduled: id={}", task.getId());
    }

//    @Scheduled(cron = "0/10 * * * * ?")
//    public void deleteTask() {
//        log.info("开始执行测试异步删除任务");
//        String params = "模擬查詢數據庫";
//
//        AsyncTask task = asyncTaskService.createTask(
//                "测试异步任务",
//                AsyncEnum.TYPE_DELETE_STAT.getCode(),
//                params
//        );
//
//        asyncTaskService.executeTask(task.getId());
//        log.info("Daily statistics task scheduled: id={}", task.getId());
//    }

}