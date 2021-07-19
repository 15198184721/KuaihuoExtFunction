package com.kuaihuo.ext.controllers;

import com.kuaihuo.ext.controllers.models.BaseResp;
import com.kuaihuo.ext.mybatis.entitys.AppCountUser;
import com.kuaihuo.ext.services.AppCountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * app用户相关统计的控制器
 *  专门用于统计记录用户相关的数据接口服务
 */
@RestController
@RequestMapping("/appUserCount")
public class AppUserCountController {

    @Autowired
    private AppCountUserService iUserService;

    /**
     * 根据指定用户id查询用户信息
     * @param userId
     * @return
     */
    @GetMapping("/findUserId/{userId}")
    public BaseResp<AppCountUser> getFindByUserId(@PathVariable("userId") String userId) {
        return BaseResp.buildRespSuccess(iUserService.getUserById(userId));
    }

}
