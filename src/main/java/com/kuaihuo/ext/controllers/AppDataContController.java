package com.kuaihuo.ext.controllers;

import com.kuaihuo.ext.controllers.models.BaseResp;
import com.kuaihuo.ext.services.AppDataCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * app数据统计的服务控制器
 */
@RestController
@RequestMapping("/appDataCount")
public class AppDataContController {

    @Autowired
    private AppDataCountService appDataCountSerbice;

    @GetMapping("/test")
    public BaseResp<String> getTestData() {
        return BaseResp.buildRespSuccess(appDataCountSerbice.getTestData());
    }

}
