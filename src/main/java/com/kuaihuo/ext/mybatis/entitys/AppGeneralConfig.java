package com.kuaihuo.ext.mybatis.entitys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * app的通用配置表
 * 注：此表是为了学习使用。为了学习 Mybatis-plus 相关用法和熟悉开发流程
 */
@Data
@TableName("app_general_config")
public class AppGeneralConfig {
    /**
     * id
     */
    @Id //不加此注解。在BaseMapper的updateById方法会报错，
    private Integer id;
    /**
     * 配置类型
     */
    private String type;
    /**
     * 配置的值
     */
    private int value;
    /**
     * 配置生效截止，就是这个配置有效期到什么时候为止
     * null:表示长期有效  生效区
     */
    private LocalDateTime validPeriod;
    /**
     * 生效区域(省级)，null表示全国范围
     */
    private String effectiveArea;
    /**
     * 描述信息，就是对这个类型的描述以及值说明
     */
    private String msg;
}
