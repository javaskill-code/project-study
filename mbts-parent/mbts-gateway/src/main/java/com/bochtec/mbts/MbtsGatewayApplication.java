package com.bochtec.mbts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bochtec.mbts.bankTransfer.mapper")
public class MbtsGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MbtsGatewayApplication.class, args);
    }
}
