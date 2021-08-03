package com.kuaihuo.ext.controllers;

import com.kuaihuo.ext.controllers.models.BaseResp;
import com.kuaihuo.ext.controllers.models.UserLoginInfoReq;
import com.kuaihuo.ext.mybatis.entitys.AppCountUser;
import com.kuaihuo.ext.services.AppCountUserService;
import com.kuaihuo.ext.utils.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * app用户相关统计的控制器
 * 专门用于统计记录用户相关的数据接口服务
 */
@RestController
@RequestMapping("/appUserCount")
public class AppUserCountController {

    @Autowired
    private AppCountUserService iUserService;

    /**
     * 根据指定用户id查询用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/findUserId/{userId}")
    public BaseResp<AppCountUser> getFindByUserId(@PathVariable("userId") String userId) {
        return BaseResp.buildRespSuccess(iUserService.getUserById(userId));
    }

    /**
     * 插入用户到数据库
     *
     * @param req 请求参数
     * @return
     */
    @PostMapping("/setUserLoginInfo")
    public BaseResp<Integer> setUserLoginInfo(@RequestBody UserLoginInfoReq req) {
        try {
            AppCountUser user = new AppCountUser();
            user.setUserId(req.getUserId());
            user.setName(req.getName());
            user.setGender(req.getGender());
            user.setLoginAddrss(req.getLoginAddrss());
            iUserService.insertOrUpdate(user);
        } catch (Exception e) {
            PrintUtil.println("更新插入用户统计出现错误了:" + e);
        }
        return BaseResp.buildRespSuccess(0);
    }

}
