package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication

public class Ruiji_take {
    public static void main(String[] args) {
        SpringApplication.run(Ruiji_take.class, args);
        log.info("项目初始化成功");
    }

}
