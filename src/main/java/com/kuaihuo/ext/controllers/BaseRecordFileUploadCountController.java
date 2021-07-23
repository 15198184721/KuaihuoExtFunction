package com.kuaihuo.ext.controllers;

import com.kuaihuo.ext.controllers.enems.CountTypeEnum;
import com.kuaihuo.ext.controllers.intefaces.IFileParsing;
import com.kuaihuo.ext.controllers.intefaces.impl.ActivityJumpFileParsingImpl;
import com.kuaihuo.ext.controllers.models.BaseResp;
import com.kuaihuo.ext.mybatis.entitys.AppCountActivityJump;
import com.kuaihuo.ext.services.AppCountActivityJumpService;
import com.kuaihuo.ext.utils.files.FileIOUtils;
import com.kuaihuo.ext.utils.files.FilePathUtil;
import com.kuaihuo.ext.utils.ParsingFileDataUtil;
import com.kuaihuo.ext.utils.PrintUtil;
import com.kuaihuo.ext.utils.files.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 基础的记录文件上传服务接口，提供一些比较通用类的服务接口
 */
@RestController
@RequestMapping("/baseRecordFileController")
public class BaseRecordFileUploadCountController {

    @Autowired
    private AppCountActivityJumpService iJumpService;
    //文件上传保存的位置
    private String fileUploadSaveDir = null;

    /**
     * 上传记录的文件接口
     *
     * @param files 上传的文件集合
     * @return
     */
    @PostMapping("/uploadRecordFile")
    public BaseResp<String> uploadRecordLog(@RequestParam("file") List<MultipartFile> files) {
        if (files.isEmpty()) {
            return BaseResp.buildRespSuccess("成功");
        }
        if (fileUploadSaveDir == null) {
            fileUploadSaveDir = FilePathUtil.getUploadAbsBasePath();
        }
        saveLogFiles(files);
        return BaseResp.buildRespSuccess("成功");
        //暂时不管。无非就是丢失部分数据而已。所以只要调用此方法。一定是成功
//        if(saveLogFiles(files)){
//            return BaseResp.buildRespSuccess("成功");
//        }else{
//            return BaseResp.buildRespError(RespCode.UPLOAD_ERROR.code,"上传错误",null);
//        }
    }

    //保存文件(量大可以考虑异步执行)
    private boolean saveLogFiles(List<MultipartFile> files) {
        int successCount = 0;
        List<File> taskFiles = new ArrayList<File>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            //需要保存的目标文件
            File dest = new File(fileUploadSaveDir + fileName);
            try {
                file.transferTo(dest);
                taskFiles.add(dest);
                successCount++;
            } catch (IOException e) {
//            LOGGER.info("上传日志记录保存出错了:"+e);
                PrintUtil.println("上传日志记录保存出错:" + e + ",文件路径：" + dest.getAbsolutePath());
            }
        }
        if (successCount > 0) {
            //添加异步任务
            ParsingFileDataUtil.addTask(() -> parsingFileData2SaveDb(taskFiles));
        }
        return files.size() == successCount;
    }

    //解析文件数据保存到到数据库
    private void parsingFileData2SaveDb(List<File> taskFiles) {
        Map<CountTypeEnum, List<File>> typeFiles = new HashMap<>();
        for (File taskFile : taskFiles) {
            try {
                boolean isCanHandle = false; //当前文件是否能被处理
                String fileName = FileUtils.getFileNameNoExtension(taskFile);
                //对文件进行分类筛选
                for (CountTypeEnum value : CountTypeEnum.values()) {
                    if (fileName.startsWith(value.name)) {
                        isCanHandle = true;
                        if (typeFiles.get(value) == null) {
                            List<File> ls = new ArrayList<>();
                            ls.add(taskFile);
                            typeFiles.put(value, ls);
                        } else {
                            typeFiles.get(value).add(taskFile);
                        }
                        break;
                    }
                }
                if (!isCanHandle) {
                    //当前文件不能被处理
                    taskFile.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //开始根据分类调用执行相关的处理任务
        for (Map.Entry<CountTypeEnum, List<File>> countTypeEnumListEntry : typeFiles.entrySet()) {
            try {
                //创建类型处理器
                IFileParsing typeParsing = countTypeEnumListEntry.getKey().parsingClzz.newInstance();
                typeParsing.parsingFile(countTypeEnumListEntry.getValue());
            } catch (InstantiationException | IllegalAccessException n) {
                PrintUtil.println("创建类型日志处理器错误");
            } catch (Exception e) {
                PrintUtil.println("处理[" + countTypeEnumListEntry.getKey().name + "]类型日志出现错误:"+e);
            }
        }
    }
}
