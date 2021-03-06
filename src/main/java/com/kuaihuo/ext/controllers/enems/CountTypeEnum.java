package com.kuaihuo.ext.controllers.enems;

import com.kuaihuo.ext.controllers.intefaces.IFileParsing;
import com.kuaihuo.ext.controllers.intefaces.impl.ActivityJumpFileParsingImpl;
import com.kuaihuo.ext.controllers.intefaces.impl.ActivityUserSalyFileParsingImpl;

/**
 * 统计分类，支持的统计分类
 */
public enum CountTypeEnum {
    //页面跳转的统计分类
    ACTIVITY_JUMP(0, "ACTIVITY_JUMP", ActivityJumpFileParsingImpl.class),
    //页面用户停留时间统计的类型
    ACTIVITY_USER_STAY(1, "ACTIVITY_USER_STAY", ActivityUserSalyFileParsingImpl.class),
    ;

    public final int code;
    public final String name;
    public final Class<? extends IFileParsing> parsingClzz; //处理分类的工具

    CountTypeEnum(int code, String name, Class<? extends IFileParsing> parsingClzz) {
        this.code = code;
        this.name = name;
        this.parsingClzz = parsingClzz;
    }
}
