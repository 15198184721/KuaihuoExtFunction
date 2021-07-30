package com.kuaihuo.ext.services;

import com.kuaihuo.ext.mybatis.entitys.AppCountUser;
import com.kuaihuo.ext.mybatis.entitys.AppGeneralConfig;
import com.kuaihuo.ext.mybatis.mappers.IAppGeneralConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * app 通用配置 Service
 */
@Service
public class AppGeneralConfigService {

    @Autowired
    private IAppGeneralConfigMapper iConfigMapper;

    /**
     * 获取app的所有通用的配置信息
     *
     * @return
     */
    public List<AppGeneralConfig> getAppConfig() {
        try {
            return iConfigMapper.selectList(null);
        } catch (Exception e) {
            return null;
        }
    }
}
