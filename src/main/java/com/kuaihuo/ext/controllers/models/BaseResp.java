package com.kuaihuo.ext.controllers.models;

import com.kuaihuo.ext.controllers.enems.RespCode;

/**
 * 返回对象的基础结构体
 *
 * @param <T>
 */
public class BaseResp<T> {

    /**
     * 创建一个成功的返回实体
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResp<T> buildRespSuccess(T data) {
        return new BaseResp<T>(RespCode.Success.code, null, data);
    }

    /**
     * 创建一个错误的返回实体
     *
     * @param msg
     * @param data
     * @return
     */
    public static <T> BaseResp<T> buildRespError(String msg, T data) {
        return new BaseResp<T>(RespCode.DATA_ERROR.code, "", data);
    }

    /**
     * 创建一个错误的返回实体
     *
     * @param msg
     * @param data
     * @return
     */
    public static <T> BaseResp<T> buildRespError(int code,String msg, T data) {
        return new BaseResp<T>(code, "", data);
    }

    public int code = 0;
    public String msg = "";
    public T data;

    private BaseResp(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
