package com.kuaihuo.ext.mybatis.entitys;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 定义一个通用字段的实体。如果有这些字段继承此类即可
 */
@Getter
@Setter
public class BaseTableEntity {
    /**
     * 创建时间
     * 注：为所有表增加通用字段，为了测试自动填充功能
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     *  注：为所有表增加通用字段，为了测试自动填充功能
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
