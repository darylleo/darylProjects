package com.daryl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author wl
 * @create 2022-02-08
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.daryl.mapper")
public class HouseApplication {
    public static void main(String[] args) {
        SpringApplication.run(HouseApplication.class, args);
    }
}
