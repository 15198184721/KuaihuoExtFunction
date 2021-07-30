package com.kuaihuo.ext.controllers;

import com.kuaihuo.ext.controllers.models.BaseResp;
import com.kuaihuo.ext.controllers.models.UserLoginInfoReq;
import com.kuaihuo.ext.controllers.models.resp.AppGeneralConfigResp;
import com.kuaihuo.ext.mybatis.entitys.AppCountUser;
import com.kuaihuo.ext.mybatis.entitys.AppGeneralConfig;
import com.kuaihuo.ext.services.AppCountUserService;
import com.kuaihuo.ext.services.AppGeneralConfigService;
import com.kuaihuo.ext.utils.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * app 配置服务接口控制器
 */
@RestController
@RequestMapping("/appConfig")
public class AppConfigController {

    @Autowired
    private AppGeneralConfigService iGeneralService;

    /**
     * 根据指定用户id查询用户信息
     *
     * @return
     */
    @PostMapping("/getAppConfig")
    public BaseResp<List<AppGeneralConfigResp>> getAppConfigs() {
        Stream<AppGeneralConfigResp> resp = iGeneralService.getAppConfig().stream().map(AppGeneralConfigResp::new);
        return BaseResp.buildRespSuccess(resp.collect(Collectors.toList()));
    }
}
