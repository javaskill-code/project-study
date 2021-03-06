package com.jiang.boot_redis01.boot_redis01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BootRedis01Application {

    public static void main(String[] args) {
        SpringApplication.run(BootRedis01Application.class, args);
    }

}
