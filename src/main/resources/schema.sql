CREATE TABLE IF NOT EXISTS async_task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '任务ID',
    task_name VARCHAR(100) NOT NULL COMMENT '任务名称',
    task_type VARCHAR(50) NOT NULL COMMENT '任务类型（DAILY_STAT, MONTHLY_STAT, ARCHIVE等）',
    task_status VARCHAR(20) NOT NULL DEFAULT 'PENDING' COMMENT '任务状态（PENDING, RUNNING, COMPLETED, FAILED）',
    task_params TEXT COMMENT '任务参数（JSON格式）',
    start_time DATETIME COMMENT '任务开始时间',
    end_time DATETIME COMMENT '任务结束时间',
    execution_time BIGINT COMMENT '执行耗时（毫秒）',
    error_message TEXT COMMENT '错误信息',
    retry_count INT DEFAULT 0 COMMENT '重试次数',
    max_retry_count INT DEFAULT 3 COMMENT '最大重试次数',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_task_type (task_type),
    INDEX idx_task_status (task_status),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='异步任务表';