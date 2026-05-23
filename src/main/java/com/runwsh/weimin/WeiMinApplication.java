package com.runwsh.weimin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync // 启用 @Async 异步注解
@EnableScheduling // 启用定时调度功能
public class WeiMinApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeiMinApplication.class, args);
    }

}
