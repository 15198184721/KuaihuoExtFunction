package com.kuaihuo.ext.mybatis.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuaihuo.ext.mybatis.entitys.User;
import org.apache.ibatis.annotations.Select;

/**
 * app数据统计数据表的数据操作映射 (mybatis数据操作映射)
 */
public interface IAppDataCountMapper extends BaseMapper<User> {

    @Select("SELECT * FROM user")
    User getTestAppCountData();

}
