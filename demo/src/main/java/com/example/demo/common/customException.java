package com.example.demo.common;

/**
 * 自定义异常
 */
public class customException extends RuntimeException {
    public customException(String message){
        super(message);
    }
}
