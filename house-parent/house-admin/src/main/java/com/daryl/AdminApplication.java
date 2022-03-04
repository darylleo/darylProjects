package com.daryl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 用户管理启动类
 * @author wl
 * @create 2022-02-08
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.daryl.mapper")
public class AdminApplication {
    public static void main(String[] args) {
         SpringApplication.run(AdminApplication.class, args);
    }
}
