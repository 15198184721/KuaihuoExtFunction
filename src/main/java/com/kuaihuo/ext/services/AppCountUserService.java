package com.kuaihuo.ext.services;

import com.kuaihuo.ext.mybatis.entitys.AppCountUser;
import com.kuaihuo.ext.mybatis.mappers.IAppCountUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * app 用户数据统计的Service
 */
@Service
public class AppCountUserService {

    @Autowired
    private IAppCountUserMapper iAppCountUserMapper;

    /**
     * 获取指定用户id的数据
     *
     * @param userId
     * @return
     */
    public AppCountUser getUserById(String userId) {
        try {
            return iAppCountUserMapper.selectById(userId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 插入或者更新数据
     *
     * @param user
     * @return
     */
    @Transactional //需要在同一个事物中执行
    public int insertOrUpdate(AppCountUser user) {
        try {
            int resu = iAppCountUserMapper.updateById(user);
            if (resu == 0) {
                resu = iAppCountUserMapper.insert(user);
            }
            return resu;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 批量插入数据
     *
     * @param users 需要插入的用户集合
     * @return
     */
    public int insertList(List<AppCountUser> users) {
        try {
            return iAppCountUserMapper.insertBatchSomeColumn(users);
        } catch (Exception e) {
            return 0;
        }
    }
}
