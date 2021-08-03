package com.kuaihuo.ext.controllers.models;

import lombok.Data;

/**
 * 用户登录的信息请求实体
 */
@Data
public class UserLoginInfoReq {
    private String userId; //用户id
    private String name; //用户名称(可为空)
    private Integer gender;//性别(可为空)
    private String loginAddrss;//最近一次登录的地址
}
