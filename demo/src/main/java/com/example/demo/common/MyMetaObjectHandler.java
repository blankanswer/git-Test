package com.example.demo.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {


        log.info("公共字段自动填充（insert）");
        log.info(metaObject.toString());

        long id = Thread.currentThread().getId();
        log.info("线程的id；{}",id);
            metaObject.setValue("updateTime",LocalDateTime.now());
            metaObject.setValue("createTime",LocalDateTime.now());
            metaObject.setValue("updateUser",baseContext.getCurrentId());
            metaObject.setValue("createUser",baseContext.getCurrentId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充（update）");
        log.info(metaObject.toString());
            metaObject.setValue("updateTime", LocalDateTime.now());
            metaObject.setValue("updateUser",baseContext.getCurrentId());

    }
}
