package com.kuaihuo.ext.components.autofill;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.kuaihuo.ext.mybatis.entitys.AppCountActivityJump;
import com.kuaihuo.ext.mybatis.entitys.AppCountActivityUserStay;
import com.kuaihuo.ext.mybatis.entitys.AppCountUser;
import com.kuaihuo.ext.mybatis.mappers.IAppCountActivityJumpMapper;
import com.kuaihuo.ext.mybatis.mappers.IAppCountUserMapper;
import com.kuaihuo.ext.utils.PrintUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * AppCountUser表 自动填充的字段的处理类
 */
@Component
public class AutoFillComponent implements MetaObjectHandler {

    @Autowired
    IAppCountUserMapper appDataCountMapper;

    @Autowired
    IAppCountActivityJumpMapper iActivityJumpMapper;

    @Override
    public void insertFill(MetaObject metaObject) {
        Class<?> clzz = metaObject.getOriginalObject().getClass();
        if (AppCountUser.class == clzz) {
            fillInsertAppCountUser(metaObject); //用户表插入的自动填充
        }else if (AppCountActivityUserStay.class == clzz) {
            fillUpdateAppCountActivityUserStay(metaObject); //用户在界面停留的统计(和更新时候一个逻辑。都是更新为当前时间)
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Class<?> clzz = metaObject.getOriginalObject().getClass();
        if (AppCountUser.class == clzz) {
            fillUpdateAppCountUser(metaObject); //用户表更新自动填充
        } else if (AppCountActivityJump.class == clzz) {
            fillUpdateAppCountActivityJump(metaObject); //页面跳转统计的更新自动填充
        }else if (AppCountActivityUserStay.class == clzz) {
            fillUpdateAppCountActivityUserStay(metaObject); //用户在界面停留的统计
        }
    }

    //填充App的页面跳转的表的自动填充
    private void fillUpdateAppCountActivityUserStay(MetaObject metaObject) {
        PrintUtil.println("更新AppCountActivityUserStay表数据的数据自动填充执行了");
        //更新时间
        if (metaObject.hasGetter("updateTime")) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }

    //填充App的页面跳转的表的自动填充
    private void fillUpdateAppCountActivityJump(MetaObject metaObject) {
        PrintUtil.println("更新AppCountActivityJump表数据的数据自动填充执行了");
    }

    //填充App的用户信息表
    private void fillUpdateAppCountUser(MetaObject metaObject) {
        PrintUtil.println("更新AppCountUser表数据的数据自动填充执行了");
        //更新时间
        if (metaObject.hasGetter("updateTime")) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
        //登录时间
        if (metaObject.hasGetter("loginTime")) {
            setFieldValByName("loginTime", LocalDateTime.now(), metaObject);
        }
        //登录次数
        if (metaObject.hasGetter("loginCount")) {
            Object userId = getFieldValByName("userId", metaObject);
            if (userId != null) {
                AppCountUser user = appDataCountMapper.selectById(userId.toString());
                if (user != null) {
                    setFieldValByName("loginCount", user.getLoginCount() + 1, metaObject);
                }
            }
        }
    }

    //填充App的用户信息表
    private void fillInsertAppCountUser(MetaObject metaObject) {
        PrintUtil.println("插入AppCountUser表数据的数据自动填充执行了");
        //创建时间
        if (metaObject.hasGetter("createTime")) {
            setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
        //更新时间
        if (metaObject.hasGetter("updateTime")) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
        //登录时间
        if (metaObject.hasGetter("loginTime")) {
            setFieldValByName("loginTime", LocalDateTime.now(), metaObject);
        }
    }
}
