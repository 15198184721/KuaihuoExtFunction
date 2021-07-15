package com.kuaihuo.ext.components;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 自动填充的字段的处理类
 */
@Component
public class AutoFillMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("---插入数据的数据自动填充执行了---");

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("---更新数据的数据自动填充执行了---");
    }
}
