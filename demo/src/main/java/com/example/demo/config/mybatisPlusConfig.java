package com.example.demo.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//注解类
public class mybatisPlusConfig {
    @Bean//用spring来管理
    public MybatisPlusInterceptor mybatisPlusInterceptor(){//mybatis拦截器
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());//分页所需拦截器
        return mybatisPlusInterceptor;
    }
}
