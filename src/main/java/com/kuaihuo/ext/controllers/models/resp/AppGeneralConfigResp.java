package com.kuaihuo.ext.controllers.models.resp;

import com.kuaihuo.ext.mybatis.entitys.AppGeneralConfig;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * app的通用配置返回实体
 */
@Data
public class AppGeneralConfigResp extends AppGeneralConfig {

    /**
     * 当前的时间(服务器的时间)
     */
    private LocalDateTime curenntTime = LocalDateTime.now();

    public AppGeneralConfigResp(AppGeneralConfig config){
        this.setId(config.getId());
        this.setType(config.getType());
        this.setValue(config.getValue());
        this.setMsg(config.getMsg());
        this.setValidPeriod(config.getValidPeriod());
        this.setEffectiveArea(config.getEffectiveArea());
    }

}
