package com.example.demo.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
@RestControllerAdvice(annotations = {RestController.class, Controller.class})//拦截加了RestController注解的通知
@Slf4j
public class globalException {
    /**
     * 异常处理方法 据说是aop的思想
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException exception){
      log.info(exception.getMessage());//这个地方是打印异常信息嘛
      if(exception.getMessage().contains("Duplicate entry")){//然后得到信息后根据关键字词 进行异常处理
          String[] s = exception.getMessage().split(" ");//用空格做分隔符 在异常信息中
          String msg = s[2] + "已存在";//这里s[2]正好是员工账号名（用户名）
          return R.error(msg);//在前端返回异常信息
      }
        return R.error("未知错误");
    }

    /**
     * 异常处理方法 据说是aop的思想
     * @return
     */
    @ExceptionHandler(customException.class)
    public R<String> exceptionHandler(customException exception){

         log.info(exception.getMessage());//这个地方是打印异常信息嘛

        return R.error(exception.getMessage());
    }


}
