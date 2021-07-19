package com.kuaihuo.ext.controllers;

import com.kuaihuo.ext.controllers.enems.RespCode;
import com.kuaihuo.ext.controllers.models.BaseResp;
import com.kuaihuo.ext.services.AppCountActivityJumpService;
import com.kuaihuo.ext.utils.FilePathUtil;
import com.kuaihuo.ext.utils.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * app用户相关统计的控制器
 * 专门用于统计记录用户相关的数据接口服务
 */
@RestController
@RequestMapping("/appActivityCount")
public class AppCountActivityController {

    @Autowired
    private AppCountActivityJumpService iJumpService;

    /**
     * 此控制器的测试方法
     *
     * @return
     */
    @GetMapping("/test")
    public BaseResp<String> test() {
        return BaseResp.buildRespSuccess("测试成功。控制器：" + this);
    }

    /**
     * 上传记录的日志
     *
     * @param file 上传的文件集
     * @return
     */
    @PostMapping("/uploadRecordLog")
    public BaseResp<String> uploadRecordLog(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return BaseResp.buildRespError(RespCode.UPLOAD_ERROR.code, "上传失败", null);
        }
        String uploadPath = FilePathUtil.getUploadAbsBasePath();
        String fileName = file.getOriginalFilename();
        //需要保存的目标文件
        File dest = new File(uploadPath + fileName);
        try {
            file.transferTo(dest);
            return BaseResp.buildRespSuccess("成功");
        } catch (IOException e) {
//            LOGGER.info("上传日志记录保存出错了:"+e);
            PrintUtil.println("上传日志记录保存出错了:" + e);
        }
        return BaseResp.buildRespError(RespCode.UPLOAD_ERROR.code, "上传失败", null);
    }

}
