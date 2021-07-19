package com.kuaihuo.ext.controllers.enems;

/**
 * 返回业务的返回码
 */
public enum RespCode {
    Success(0), //网络请求成功
    DATA_ERROR(1), //数据错误
    UPLOAD_ERROR(2), //文件上传错误
    ;

    public final int code;

    RespCode(int code){
        this.code = code;
    }
}
