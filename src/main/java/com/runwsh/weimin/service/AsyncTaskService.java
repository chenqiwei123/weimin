package com.runwsh.weimin.service;

import com.runwsh.weimin.asyncEnum.AsyncEnum;
import com.runwsh.weimin.entity.AsyncTask;
import com.runwsh.weimin.handler.AsyncTaskHandler;
import com.runwsh.weimin.handler.AsyncTaskHandlerRegistry;
import com.runwsh.weimin.mapper.AsyncTaskMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 异步任务服务 - 可复用版本
 * 通过 AsyncTaskHandler 接口支持自定义业务逻辑
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncTaskService {

    private final AsyncTaskMapper asyncTaskMapper;
    private final AsyncTaskHandlerRegistry handlerRegistry;

    /**
     * 创建异步任务
     */
    @Transactional
    public AsyncTask createTask(String taskName, String taskType, String taskParams) {
        AsyncTask task = new AsyncTask();
        task.setTaskName(taskName);
        task.setTaskType(taskType);
        task.setTaskStatus(AsyncEnum.STATUS_PENDING.getCode());
        task.setTaskParams(taskParams);
        task.setRetryCount(0);
        task.setMaxRetryCount(3);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        asyncTaskMapper.insert(task);
        log.info("Created async task: id={}, name={}, type={}", task.getId(), taskName, taskType);
        return task;
    }

    /**
     * 异步执行任务 - 可复用版本
     * 通过注册表动态获取对应的任务处理器来执行业务逻辑
     */
    @Async("asyncTaskExecutor")
    public void executeTask(Long taskId) {
        AsyncTask task = asyncTaskMapper.selectById(taskId);
        if (task == null) {
            log.error("Task not found: {}", taskId);
            return;
        }

        // 检查是否有对应的处理器
        if (!handlerRegistry.hasHandler(task.getTaskType())) {
            log.error("No handler found for task type: {}", task.getTaskType());
            handleTaskFailure(taskId, 0L, "No handler found for task type: " + task.getTaskType());
            return;
        }

        updateTaskStatus(taskId, AsyncEnum.STATUS_RUNNING.getCode());
        task.setStartTime(LocalDateTime.now());

        long startTime = System.currentTimeMillis();
        try {
            log.info("Executing task: id={}, name={}, type={}", taskId, task.getTaskName(), task.getTaskType());
            
            // 从注册表获取对应的处理器并执行
            AsyncTaskHandler handler = handlerRegistry.getHandler(task.getTaskType());
            handler.execute(task.getTaskParams());

            long executionTime = System.currentTimeMillis() - startTime;
            completeTask(taskId, executionTime);
            log.info("Task completed: id={}, executionTime={}ms", taskId, executionTime);
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            handleTaskFailure(taskId, executionTime, e.getMessage());
            log.error("Task failed: id={}, error={}", taskId, e.getMessage(), e);
        }
    }

    @Transactional
    public void updateTaskStatus(Long taskId, String status) {
        AsyncTask task = asyncTaskMapper.selectById(taskId);
        if (task != null) {
            task.setTaskStatus(status);
            task.setUpdatedAt(LocalDateTime.now());
            asyncTaskMapper.updateById(task);
        }
    }

    @Transactional
    public void completeTask(Long taskId, long executionTime) {
        AsyncTask task = asyncTaskMapper.selectById(taskId);
        if (task != null) {
            task.setTaskStatus(AsyncEnum.STATUS_COMPLETED.getCode());
            task.setEndTime(LocalDateTime.now());
            task.setExecutionTime(executionTime);
            task.setUpdatedAt(LocalDateTime.now());
            asyncTaskMapper.updateById(task);
        }
    }

    @Transactional
    public void handleTaskFailure(Long taskId, long executionTime, String errorMessage) {
        AsyncTask task = asyncTaskMapper.selectById(taskId);
        if (task != null) {
            task.setRetryCount(task.getRetryCount() + 1);
            task.setErrorMessage(errorMessage);
            task.setEndTime(LocalDateTime.now());
            task.setExecutionTime(executionTime);
            task.setUpdatedAt(LocalDateTime.now());

            if (task.getRetryCount() >= task.getMaxRetryCount()) {
                task.setTaskStatus(AsyncEnum.STATUS_FAILED.getCode());
            } else {
                task.setTaskStatus(AsyncEnum.STATUS_PENDING.getCode());
                log.info("Task will be retried: id={}, retryCount={}/{}", 
                        taskId, task.getRetryCount(), task.getMaxRetryCount());
            }
            asyncTaskMapper.updateById(task);
        }
    }
}
