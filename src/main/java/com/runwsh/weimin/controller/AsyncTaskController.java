package com.runwsh.weimin.controller;

import com.runwsh.weimin.asyncEnum.AsyncEnum;
import com.runwsh.weimin.entity.AsyncTask;
import com.runwsh.weimin.service.AsyncTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class AsyncTaskController {

    private final AsyncTaskService asyncTaskService;

    @PostMapping("/daily-stat")
    public ResponseEntity<String> triggerDailyStat() {
        String date = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String params = String.format("{\"date\":\"%s\"}", date);
        
        AsyncTask task = asyncTaskService.createTask(
                "手动触发-测试任务-" + date,
                AsyncEnum.TYPE_TEST_STAT.getCode(),
                params
        );
        
        asyncTaskService.executeTask(task.getId());
        log.info("Manually triggered daily stat task: id={}", task.getId());
        return ResponseEntity.ok("Daily statistics task triggered: " + task.getId());
    }


}