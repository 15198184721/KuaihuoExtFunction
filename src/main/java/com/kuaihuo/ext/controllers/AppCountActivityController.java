package com.kuaihuo.ext.controllers;

import com.kuaihuo.ext.controllers.models.BaseResp;
import com.kuaihuo.ext.services.AppCountActivityJumpService;
import com.kuaihuo.ext.utils.files.FilePathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * app用户相关统计的控制器
 * 专门用于统计记录用户相关的数据接口服务
 */
@RestController
@RequestMapping("/count/appActivityCount")
public class AppCountActivityController {

    @Autowired
    private AppCountActivityJumpService iJumpService;
    //文件上传保存的位置
    private String fileUploadSaveDir = null;

    /**
     * 此控制器的测试方法
     *
     * @return
     */
    @GetMapping("/test")
    public BaseResp<String> test() {
        String root = System.getProperty("user.dir");
        StringBuffer sb = new StringBuffer();
        sb.append("classPath = " + FilePathUtil.getUploadAbsBasePath() + "\t");
        sb.append("user.dir = " + root + "\t");
        sb.append("父级 = " + new File(root).getParent() + "\t");
        return BaseResp.buildRespSuccess(sb.toString());
    }

}
