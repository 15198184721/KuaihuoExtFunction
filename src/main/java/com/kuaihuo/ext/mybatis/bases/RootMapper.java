package com.kuaihuo.ext.mybatis.bases;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

import java.util.Collection;
import java.util.List;

/**
 * 扩展mybatis 的 BaseMapper 的基础操作方法
 * @param <T>
 */
public interface RootMapper<T> extends BaseMapper<T> {
    /**
     * 批量插入 仅适用于mysql,方法名称和注入的方法对象 {@link InsertBatchSomeColumn#getMethod(SqlMethod)} 所返回的方法一致
     *
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(Collection<T> entityList);
}
