package com.kuaihuo.ext.components.sqlinject.injects;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

import java.util.List;

/**
 * 支持自定的数据方法注入,可以堆 Mybatis 的方法进行扩展注入,扩展基础操作方法之外的操作到容器中：
 * 通过此方式写了之后的就不在需要给对应的接口去些xml中的sql了。相当于完成了xml中sql这一步
 */
public class MybatisSqlInject extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> superMethods = super.getMethodList(mapperClass);
        superMethods.add(new InsertBatchSomeColumn(tableFieldInfo ->
                tableFieldInfo.getFieldFill() != FieldFill.UPDATE //不要更新方法
        )); //注入插入多行的方法到列表中(原始的 mybatis-puls 默认没有支持的方法)
        return superMethods;
    }
}
