package com.runwsh.weimin.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 异步任务处理器注册表
 * 管理所有已注册的 AsyncTaskHandler 实现
 */
@Slf4j
@Component
public class AsyncTaskHandlerRegistry {

    private final Map<String, AsyncTaskHandler> handlerMap = new HashMap<>();

    @Autowired(required = false)
    private List<AsyncTaskHandler> handlers;

    @PostConstruct
    public void init() {
        if (handlers != null && !handlers.isEmpty()) {
            for (AsyncTaskHandler handler : handlers) {
                registerHandler(handler);
            }
            log.info("Async task handlers registered: {}", handlerMap.keySet());
        }
    }

    /**
     * 注册任务处理器
     */
    public void registerHandler(AsyncTaskHandler handler) {
        String taskType = handler.getTaskType();
        if (handlerMap.containsKey(taskType)) {
            log.warn("Task type {} already registered, will overwrite", taskType);
        }
        handlerMap.put(taskType, handler);
        log.info("Registered task handler: {}", taskType);
    }

    /**
     * 根据任务类型获取处理器
     */
    public AsyncTaskHandler getHandler(String taskType) {
        return handlerMap.get(taskType);
    }

    /**
     * 检查是否存在该任务类型的处理器
     */
    public boolean hasHandler(String taskType) {
        return handlerMap.containsKey(taskType);
    }
}
