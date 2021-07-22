package com.kuaihuo.ext.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kuaihuo.ext.mybatis.entitys.AppCountActivityJump;
import com.kuaihuo.ext.mybatis.entitys.AppCountUser;
import com.kuaihuo.ext.mybatis.entitys.AppDictionaryActivitys;
import com.kuaihuo.ext.mybatis.mappers.IAppCountActivityJumpMapper;
import com.kuaihuo.ext.mybatis.mappers.IAppCountUserMapper;
import com.kuaihuo.ext.mybatis.mappers.IAppDictionaryActivitysMapper;
import com.kuaihuo.ext.utils.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.UpdateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.List;

/**
 * app 页面跳转路径丛集的Service
 */
@Service
public class AppCountActivityJumpService {

    @Autowired
    private IAppCountActivityJumpMapper iJumpMapper;
    @Autowired
    private IAppCountActivityJumpMapper iAppDictionaryModelTagMapper; //分类字典
    @Autowired
    private IAppDictionaryActivitysMapper iAppDictionaryActivitysMapper; //页面说明字典

    /**
     * 根据当前页面id和去往页面的id。查询跳转记录
     *
     * @param thisActivity 当前页面的id
     * @param toActivity   去往页面的id
     * @return
     */
    public AppCountActivityJump getFindById(String thisActivity, String toActivity) {
        try {
            return iJumpMapper.selectOne(
                    new QueryWrapper<AppCountActivityJump>()
                            .eq("this_activity", thisActivity)
                            .eq("to_activity", toActivity)
            );
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 插入或者更新全量数据
     *
     * @param jumpItem
     * @return 小于0表示执行出错
     */
    @Transactional //需要在同一个事物中执行
    public int insertOrUpdate(AppCountActivityJump jumpItem) {
        try {
            if (getFindById(jumpItem.getThisActivity(), jumpItem.getToActivity()) == null) {
                return iJumpMapper.insert(jumpItem);
            } else {
                buildUpdateCountJump(jumpItem);
                return iJumpMapper.update(jumpItem, new UpdateWrapper<AppCountActivityJump>()
                        .eq("this_activity", jumpItem.getThisActivity())
                        .eq("to_activity", jumpItem.getToActivity())
                );
            }
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 插入全量数据。或者只更新总数字段数据
     *
     * @param jumpItem
     * @return 小于0表示执行出错
     */
    @Transactional //需要在同一个事物中执行
    public int insertOrUpdateTotalCount(AppCountActivityJump jumpItem) {
        try {
            if (getFindById(jumpItem.getThisActivity(), jumpItem.getToActivity()) == null) {
                return iJumpMapper.insert(jumpItem);
            } else {
                buildUpdateCountJump(jumpItem);
                return iJumpMapper.update(null, new UpdateWrapper<AppCountActivityJump>()
                        .set("total_count", jumpItem.getTotalCount())
                        .eq("this_activity", jumpItem.getThisActivity())
                        .eq("to_activity", jumpItem.getToActivity())
                );
            }
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 批量插入数据
     *
     * @param acts 需要插入的集合
     * @return
     */
    public int insertList(List<AppCountActivityJump> acts) {
        try {
            return iJumpMapper.insertBatchSomeColumn(acts);
        } catch (Exception e) {
            return 0;
        }
    }

    //重新构建实体对象。达到自动更新累加统计字段的目的
    private void buildUpdateCountJump(AppCountActivityJump jumpItem) {
        AppCountActivityJump jumpActivity = iJumpMapper.selectOne(
                new QueryWrapper<AppCountActivityJump>()
                        .eq("this_activity", jumpItem.getThisActivity())
                        .eq("to_activity", jumpItem.getToActivity())
        );
        if (jumpActivity != null) {
            if (jumpItem.getTotalCount() == null || jumpItem.getTotalCount() == 0 || jumpItem.getTotalCount() == 1) {
                jumpItem.setTotalCount(jumpActivity.getTotalCount() + 1);
            } else {
                jumpItem.setTotalCount(jumpActivity.getTotalCount() + jumpItem.getTotalCount());
            }
        }
    }
}
