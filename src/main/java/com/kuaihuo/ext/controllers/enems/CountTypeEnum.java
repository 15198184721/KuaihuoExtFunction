package com.kuaihuo.ext.controllers.enems;

/**
 * 统计分类，支持的统计分类
 */
public enum CountTypeEnum {
    ACTIVITY_JUP(0), //页面跳转的统计分类
    ;

    public final int code;

    CountTypeEnum(int code){
        this.code = code;
    }
}
