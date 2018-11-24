package com.czy.mybatisgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.czy.mybatisgenerator.mapper")
public class MybatisGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisGeneratorApplication.class, args);
    }
}
