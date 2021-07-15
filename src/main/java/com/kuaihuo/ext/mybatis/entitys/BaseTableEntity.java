package com.kuaihuo.ext.mybatis.entitys;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 所有数据库实体的基础字段类,新建的所有表都需要包含此实体中的字段
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
