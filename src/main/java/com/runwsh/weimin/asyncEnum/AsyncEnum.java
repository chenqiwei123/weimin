package com.runwsh.weimin.asyncEnum;

/**
 * 异步任务枚举类
 * 统一管理任务状态和任务类型
 */
public enum AsyncEnum {

    // ==================== 任务状态枚举 ====================
    
    /**
     * 待执行状态
     */
    STATUS_PENDING("PENDING", "待执行"),
    
    /**
     * 执行中状态
     */
    STATUS_RUNNING("RUNNING", "执行中"),
    
    /**
     * 已完成状态
     */
    STATUS_COMPLETED("COMPLETED", "已完成"),
    
    /**
     * 失败状态
     */
    STATUS_FAILED("FAILED", "执行失败"),

    // ==================== 任务类型枚举 ====================
    
    /**
     * 每日统计任务
     */
    TYPE_DAILY_STAT("DAILY_STAT", "每日统计"),
    
    /**
     * 每月统计任务
     */
    TYPE_MONTHLY_STAT("MONTHLY_STAT", "每月统计"),
    
    /**
     * 数据归档任务
     */
    TYPE_ARCHIVE("ARCHIVE", "数据归档"),
    
    /**
     * 测试任务
     */
    TYPE_TEST_STAT("TEST_STAT", "测试任务"),
    
    /**
     * 测试插入任务
     */
    TYPE_INSERT_STAT("TYPE_INSERT_STAT", "测试插入"),
    
    /**
     * 测试删除任务
     */
    TYPE_DELETE_STAT("TYPE_DELETE_STAT", "测试删除");
    


    // ==================== 枚举属性 ====================
    
    /**
     * 枚举码，用于数据库存储
     */
    private final String code;
    
    /**
     * 枚举描述，用于显示
     */
    private final String desc;

    // ==================== 构造方法 ====================
    
    AsyncEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    // ==================== 公共方法 ====================
    
    /**
     * 获取枚举码
     */
    public String getCode() {
        return code;
    }
    
    /**
     * 获取枚举描述
     */
    public String getDesc() {
        return desc;
    }
    
    /**
     * 根据枚举码获取枚举实例
     */
    public static AsyncEnum getByCode(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }
        for (AsyncEnum asyncEnum : values()) {
            if (asyncEnum.getCode().equals(code)) {
                return asyncEnum;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return this.code;
    }
}
