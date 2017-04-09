package com.dafycredit.mall.catelog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages= "com.dafycredit.mall.catelog.dao.mapper")
public class ComDafycreditMallCatelogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComDafycreditMallCatelogApplication.class, args);
    }
}
