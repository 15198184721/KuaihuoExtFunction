package com.kuaihuo.ext.mybatis.entitys;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * app的字典,记录所有Activity的字典
 */
@Data
@TableName("app_count_activity_jump")
public class AppCountActivityJump {
    /**
     * 当前页面的路径
     */
    @Id //不加此注解。在BaseMapper的updateById方法会报错，
    private String thisActivity;
    /**
     * 去往的页面页面路径
     */
    @Id
    private String toActivity;
    /**
     * 跳转的总次数(只会做累加,在接口中值一定不会被覆盖。永远都是原值+当前值)
     */
    @TableField(value = "total_count", fill = FieldFill.UPDATE)
    private Integer totalCount = null;
}
