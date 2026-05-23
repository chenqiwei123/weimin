package com.runwsh.weimin.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 异步任务实体类
 * 用于记录和管理异步任务的执行状态、执行时间、失败重试等信息
 */
@Data
public class AsyncTask {
    
    /**
     * 任务主键ID
     */
    private Long id;
    
    /**
     * 任务名称，用于标识任务
     */
    private String taskName;
    
    /**
     * 任务类型：DAILY_STAT（每日统计）、MONTHLY_STAT（每月统计）、ARCHIVE（数据归档）等
     */
    private String taskType;
    
    /**
     * 任务状态：PENDING（待执行）、RUNNING（执行中）、COMPLETED（已完成）、FAILED（失败）
     */
    private String taskStatus;
    
    /**
     * 任务参数，JSON格式存储，用于传递业务所需参数
     */
    private String taskParams;
    
    /**
     * 任务开始执行时间
     */
    private LocalDateTime startTime;
    
    /**
     * 任务结束执行时间（成功或失败都记录）
     */
    private LocalDateTime endTime;
    
    /**
     * 任务执行耗时，单位：毫秒
     */
    private Long executionTime;
    
    /**
     * 任务失败时的错误信息
     */
    private String errorMessage;
    
    /**
     * 已重试次数
     */
    private Integer retryCount;
    
    /**
     * 最大重试次数
     */
    private Integer maxRetryCount;
    
    /**
     * 任务创建时间
     */
    private LocalDateTime createdAt;
    
    /**
     * 任务最后更新时间
     */
    private LocalDateTime updatedAt;



}
