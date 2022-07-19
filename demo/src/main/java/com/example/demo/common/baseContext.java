package com.example.demo.common;

/**
 * 基于ThreadLocal封装的工具类 封装静态方法
 */
public class baseContext  {
    private static ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
