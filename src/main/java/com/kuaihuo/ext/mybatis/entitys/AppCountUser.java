package com.kuaihuo.ext.mybatis.entitys;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 用户表对应的实体，和数据库表名一一对应
 * 注：此表是为了学习使用。为了学习 Mybatis-plus 相关用法和熟悉开发流程
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("app_count_user")
public class AppCountUser extends BaseTableEntity {
    /**
     * 用户id，必填,其他字段都是选填的
     */
    @TableId //不加此注解。在BaseMapper的updateById方法会报错，
    private String userId;
    /**
     * 用户名称(选填)
     */
    private String name;
    /**
     * 性别，0:女  1:男
     */
    private int gender;
    /**
     * 用户最近一次的登录地点(大概位置)
     */
    private String loginAddrss;
    /**
     * 最后的登录时间
     *  注：为所有表增加通用字段，为了测试自动填充功能
     */
    @TableField(value = "login_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime loginTime;
    /**
     * 最后的登录时间
     *  注：为所有表增加通用字段，为了测试自动填充功能
     */
    @TableField(value = "login_count",fill = FieldFill.UPDATE)
    private int loginCount;
    /**
     * 是否已经删除,0:未删除  1:已删除
     */
    private int del;
}
