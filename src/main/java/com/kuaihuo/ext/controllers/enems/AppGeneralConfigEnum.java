package com.kuaihuo.ext.controllers.enems;

/**
 * 通用记录表配置类型。目前支持的配置类型
 */
public enum AppGeneralConfigEnum {
    /**
     * 是否公祭日的配置
     *  0:否
     *  1：是
     */
    PUBLIC_SACRIFICE("public_sacrifice")
    ;
    public String type;

    AppGeneralConfigEnum(String name) {
        this.type = name;
    }
}
