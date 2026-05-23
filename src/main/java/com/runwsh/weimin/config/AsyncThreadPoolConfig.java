package com.runwsh.weimin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
// 启用 @Async 异步注解功能
@EnableAsync 
public class AsyncThreadPoolConfig {

    @Bean(name = "asyncTaskExecutor")
    public Executor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // 核心线程数：5个，始终保持活跃
        executor.setMaxPoolSize(10); // 最大线程数：10个，超过核心线程数时，会创建新线程
        executor.setQueueCapacity(100); // 任务列容量：100个，超过此容量时，会拒绝新任务
        executor.setKeepAliveSeconds(60); // 空闲线程保持时间：60秒，超过此时间的空闲线程会被被销毁
        executor.setThreadNamePrefix("async-task-"); // 线程名称前缀：async-task-
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝策略：CallerRunsPolicy，调用者运行策略
        executor.setWaitForTasksToCompleteOnShutdown(true); // 等待所有任务完成后再关闭线程池
        executor.setAwaitTerminationSeconds(60); // 等待线程池关闭超时时间：60秒
        executor.initialize(); // 初始化线程池
        return executor;
    }
}