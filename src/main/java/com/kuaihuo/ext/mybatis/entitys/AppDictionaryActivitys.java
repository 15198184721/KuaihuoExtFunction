package com.kuaihuo.ext.mybatis.entitys;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * app的字典,记录所有Activity的字典
 */
@Data
@TableName("app_dictionary_activitys")
public class AppDictionaryActivitys {
    /**
     * 字典的id
     */
    @TableId //不加此注解。在BaseMapper的updateById方法会报错，
    private String id;
    /**
     * 字典所代表的的Activity页面
     */
    private String name;
    /**
     * desc
     */
    private String desc;
}
