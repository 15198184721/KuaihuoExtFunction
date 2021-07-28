package com.kuaihuo.ext.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kuaihuo.ext.mybatis.entitys.AppCountActivityJump;
import com.kuaihuo.ext.mybatis.entitys.AppCountActivityUserStay;
import com.kuaihuo.ext.mybatis.mappers.IAppCountActivityJumpMapper;
import com.kuaihuo.ext.mybatis.mappers.IAppCountActivityUserStayMapper;
import com.kuaihuo.ext.mybatis.mappers.IAppDictionaryActivitysMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * app 页面跳转路径丛集的Service
 */
@Service
public class AppCountActivityUserStayService {

    @Autowired
    private IAppCountActivityUserStayMapper iUserStayMapper;

    /**
     * 根据当前页面id和去往页面的id。查询跳转记录
     *
     * @param userId       用户id
     * @param stayActivity 停留的页面
     * @return
     */
    public AppCountActivityUserStay getFindById(String userId, String stayActivity) {
        try {
            return iUserStayMapper.selectOne(
                    new QueryWrapper<AppCountActivityUserStay>()
                            .eq("user_id", userId)
                            .eq("stay_activity", stayActivity)
            );
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 插入全量数据。或者只更新总数字段数据
     *
     * @param stayItem
     * @return 小于0表示执行出错
     */
    @Transactional //需要在同一个事物中执行
    public int insertOrUpdateTotalCount(AppCountActivityUserStay stayItem) {
        try {
            AppCountActivityUserStay oldData = getFindById(stayItem.getUserId(), stayItem.getStayActivity());
            if (oldData == null) {
                return iUserStayMapper.insert(stayItem);
            } else {
                buildUpdateCountJump(oldData, stayItem);
                return iUserStayMapper.update(stayItem, new UpdateWrapper<AppCountActivityUserStay>()
                        .set("stay_time", stayItem.getStayTime())
                        .eq("user_id", stayItem.getUserId())
                        .eq("stay_activity", stayItem.getStayActivity())
                );
            }
        } catch (Exception e) {
            return -1;
        }
    }

    //重新构建实体对象。达到自动更新累加统计字段的目的
    private void buildUpdateCountJump(AppCountActivityUserStay oldData, AppCountActivityUserStay jumpItem) {
        if (oldData != null) {
            if (jumpItem.getStayTime() == null || jumpItem.getStayTime() <= 0) {
                jumpItem.setStayTime(oldData.getStayTime());
            } else {
                jumpItem.setStayTime(oldData.getStayTime() + jumpItem.getStayTime());
            }
        }
    }
}
