package com.kuaihuo.ext.mybatis.entitys;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表对应的实体，和数据库表名一一对应
 * 注：此表是为了学习使用。为了学习 Mybatis-plus 相关用法和熟悉开发流程
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseTableEntity {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 年龄
     */
    private int age;
    /**
     * 性别，0:女  1:男
     */
    private int gender;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String tel;

    /**
     * 是否已经删除,0:未删除  1:已删除
     */
    private int del;
}
