package com.example.demo.common;

import lombok.Data;
import lombok.Value;

import java.awt.image.Kernel;
import java.io.Serializable;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * 结果返回类,服务端返回的数据最终都会封装成此对象
 * 封装成对象的时候，这个对象可以封装具体返回信息+操作是否正确的信息 比较方便
 * 以后都返回一个R对象结果就比较统一 可以快速解析结果
 * @param <T>
 */
@Data//这个data很关键啊卧槽，是因为这个报406了
public class R<T> implements Serializable {
    private Integer code;//编码 返回结果1： 成功 返回结果 0 失败

    private String msg;// 错误信息

    private T data;//数据

    private Map map=new HashMap();//动态数据

    public static <T> R<T> sucess(T object){
        R<T> r = new R<>();
        r.data=object;
        r.code=1;
        return r;
    }
    public static <T> R<T> error(String msg){
        R<T> r = new R<>();
        r.code=0;
        r.msg=msg;
        return r;
    }
    public R<T> add(String Key,Object Value){
        this.map.put(Key, Value);
        return this;
    }
}
