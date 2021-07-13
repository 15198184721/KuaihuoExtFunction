package com.kuaihuo.ext.services;

import com.kuaihuo.ext.mybatis.mappers.IAppDataCountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * app数据统计的服务类。用于处理数据库之间的服务
 */
@Service
public class AppDataCountService {

    @Autowired
    private IAppDataCountMapper appDataCountMapper;

    /**
     * 获取测试数据
     * @return 测试数据
     */
    public String getTestData() {
        try {
            return appDataCountMapper.getTestAppCountData().id;
        }catch (Exception e){
            return "123456789";
        }
    }

}
