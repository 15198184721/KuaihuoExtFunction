package com.kuaihuo.ext.controllers.intefaces;

import java.io.File;
import java.util.List;

/**
 * 日志文件处理的基础接口
 */
public interface IFileParsing {
    /**
     * 处理多个文件
     *
     * @param files
     */
    void parsingFile(List<File> files);

    /**
     * 设置我debug模式。也就是是以debug模式插入。㤇插入到debug数据表
     */
    void setDebug();
}
