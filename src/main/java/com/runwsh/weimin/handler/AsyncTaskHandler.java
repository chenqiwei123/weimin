package com.runwsh.weimin.handler;

/**
 * 异步任务处理器接口
 * 所有自定义异步任务都需要实现这个接口
 */
public interface AsyncTaskHandler {

    /**
     * 获取任务类型，用于注册和识别
     * @return 任务类型标识，如 "DAILY_STAT", "MONTHLY_STAT" 等
     */
    String getTaskType();

    /**
     * 执行任务逻辑
     * @param taskParams 任务参数，JSON格式字符串
     */
    void execute(String taskParams);
}
