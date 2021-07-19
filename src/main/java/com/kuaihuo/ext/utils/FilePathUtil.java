package com.kuaihuo.ext.utils;

import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;

/**
 * 各种文件路径处理
 */
public class FilePathUtil {

    /**
     * 获取上传文件时候保存基础文件夹的绝对路径
     * 在开发测试模式时，得到的地址为：{项目跟目录}/target/static/uploads/
     * 在打包成jar正式发布时，得到的地址为：{发布jar包目录}static/uploads/
     *
     * @return
     */
    public static String getUploadAbsBasePath() {
        try {
            String path = ResourceUtils.getURL("classpath:").getPath();
            File upload = new File(path, "static/uploads");
            if (!upload.exists()) upload.mkdirs();
            return upload.getAbsolutePath() + File.separator;
        } catch (Exception e) {
            return null;
        }
    }

}
