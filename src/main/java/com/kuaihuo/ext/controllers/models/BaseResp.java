package com.kuaihuo.ext.controllers.models;

/**
 * 返回对象的基础结构体
 *
 * @param <T>
 */
public class BaseResp<T> {
    public int code = 0;
    public String msg = "";
    public T data;

    public static <T> BaseResp<T> buildResp(T data){

    }
}
