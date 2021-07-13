package com.kuaihuo.ext.mybatis.mappers;

import com.kuaihuo.ext.mybatis.entitys.AppDataCountEntity;
import org.apache.ibatis.annotations.Select;

/**
 * app数据统计数据表的数据操作映射 (mybatis数据操作映射)
 */
public interface IAppDataCountMapper {

    @Select("SELECT * FROM test")
    AppDataCountEntity getTestAppCountData();

}
