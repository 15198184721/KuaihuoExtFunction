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
     * 服务器得到的地址为：/usr/java/tomcat/apache-tomcat-8.5.69/static/uploads/
     *
     * @return
     */
    public static String getUploadAbsBasePath() {
        try {
            String path = getProjectRootParentDir();
            File upload = new File(path, "static/uploads");
            if (!upload.exists()) upload.mkdirs();
            return upload.getAbsolutePath() + File.separator;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取项目根目录的父级目录,防止项目更新导致内容丢失。将项目资源存储到项目之外去
     * 服务器得到的地址为：/usr/java/tomcat/apache-tomcat-8.5.69/
     *
     * @return
     */
    public static String getProjectRootParentDir() {
        String projectRootDir = System.getProperty("user.dir");
//            String path = ResourceUtils.getURL("classpath:").getPath();
        return new File(projectRootDir).getParent() + File.separator;
    }

}
