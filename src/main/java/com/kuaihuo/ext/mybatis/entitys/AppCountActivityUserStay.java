package com.kuaihuo.ext.mybatis.entitys;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * app的用户在界面的停留时间统计实体
 */
@Data
@TableName("app_count_activity_user_stay")
public class AppCountActivityUserStay {
    /**
     * 当前的用户
     */
    @Id //不加此注解。在BaseMapper的updateById方法会报错，
    private String userId;
    /**
     * 停留的页面
     */
    @Id
    private String stayActivity;
    /**
     * 当前页面停留的总时长(只会做累加)
     */
    private Integer stayTime = null;
    /**
     * 更新的时间。可以看做最后一次访问该页面的时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime = null;
}
