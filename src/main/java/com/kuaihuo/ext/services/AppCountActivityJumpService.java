package com.kuaihuo.ext.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuaihuo.ext.mybatis.entitys.AppCountActivityJump;
import com.kuaihuo.ext.mybatis.entitys.AppCountUser;
import com.kuaihuo.ext.mybatis.entitys.AppDictionaryActivitys;
import com.kuaihuo.ext.mybatis.mappers.IAppCountActivityJumpMapper;
import com.kuaihuo.ext.mybatis.mappers.IAppCountUserMapper;
import com.kuaihuo.ext.mybatis.mappers.IAppDictionaryActivitysMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * app 页面跳转路径丛集的Service
 */
@Service
public class AppCountActivityJumpService {

    @Autowired
    private IAppCountActivityJumpMapper iJumpMapper;

    @Autowired
    private IAppDictionaryActivitysMapper iAppDictionaryActivitysMapper;

    /**
     * 根据当前页面id和去往页面的id。查询跳转记录
     *
     * @param thisActivityId 当前页面的id
     * @param toActivityId   去往页面的id
     * @return
     */
    public AppCountActivityJump getFindById(String thisActivityId, String toActivityId) {
        try {
            return iJumpMapper.selectOne(
                    new QueryWrapper<AppCountActivityJump>()
                            .eq("this_activity_id", thisActivityId)
                            .eq("to_activity_id", toActivityId)
            );
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 插入或者更新数据
     *
     * @param jumpItem
     * @return
     */
    @Transactional //需要在同一个事物中执行
    public int insertOrUpdate(AppCountActivityJump jumpItem) {
        try {
            int resu = iJumpMapper.updateById(jumpItem);
            if (resu == 0) {
                try {
                    resu = iJumpMapper.insert(jumpItem);
                    AppDictionaryActivitys thisAct = iAppDictionaryActivitysMapper.selectOne(
                            new QueryWrapper<AppDictionaryActivitys>()
                                    .eq("name", jumpItem.getThisActivity())
                    );
                    if(thisAct == null){
                        AppDictionaryActivitys item = new AppDictionaryActivitys();
                        item.setName(jumpItem.getThisActivity());
                        iAppDictionaryActivitysMapper.insert(item);
                    }
                    AppDictionaryActivitys toAct = iAppDictionaryActivitysMapper.selectOne(
                            new QueryWrapper<AppDictionaryActivitys>()
                                    .eq("name", jumpItem.getToActivity())
                    );
                    if(toAct == null){
                        AppDictionaryActivitys item = new AppDictionaryActivitys();
                        item.setName(jumpItem.getToActivity());
                        iAppDictionaryActivitysMapper.insert(item);
                    }
                } catch (Exception inEx) {

                }
            }
            return resu;
        } catch (Exception e) {
            return 0;
        }
    }

//    /**
//     * 批量插入数据
//     *
//     * @param users 需要插入的用户集合
//     * @return
//     */
//    public int insertList(List<AppCountUser> users) {
//        try {
//            return iAppCountUserMapper.insertBatchSomeColumn(users);
//        } catch (Exception e) {
//            return 0;
//        }
//    }
}
