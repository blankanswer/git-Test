package com.example.demo.config;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.example.demo.common.JacksonObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 设置静态资源映射
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射");
        //映射文件目录
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

    /**
     *
     * @param converters
     */

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("扩展消息转换器");

        //创建消息转换器
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter=new MappingJackson2HttpMessageConverter();
        //设置对象转换器 底层就是用jackson把java对象转化为json对象啊
       mappingJackson2HttpMessageConverter.setObjectMapper(new JacksonObjectMapper());
        //将上面的消息追加到mvc容器中
        converters.add(0,mappingJackson2HttpMessageConverter);
    }
}
