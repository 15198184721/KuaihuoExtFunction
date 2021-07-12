package com.kuaihuo.ext.services;

import org.springframework.stereotype.Service;

/**
 * app数据统计的服务类。用于处理数据库之间的服务
 */
@Service
public class AppDataCountService {

    /**
     * 获取测试数据
     * @return 测试数据
     */
    public String getTestData() {
        return "123456789";
    }

}
