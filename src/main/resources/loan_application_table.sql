-- 贷款申请表
CREATE TABLE IF NOT EXISTS loan_applications (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    amount DECIMAL(15, 2) NOT NULL COMMENT '贷款金额',
    term INT NOT NULL COMMENT '贷款期限(月)',
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING' COMMENT '状态(PENDING/APPROVED/REJECTED)',
    reason TEXT COMMENT '贷款原因',
    applied_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
    reviewed_at DATETIME COMMENT '审核时间',
    
    INDEX idx_user_id (user_id),
    INDEX idx_status (status),
    INDEX idx_applied_at (applied_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='贷款申请表';
