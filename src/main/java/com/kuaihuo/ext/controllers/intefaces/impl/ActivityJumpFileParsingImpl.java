package com.kuaihuo.ext.controllers.intefaces.impl;

import com.kuaihuo.ext.components.beanutils.BeanUtil;
import com.kuaihuo.ext.controllers.enems.CountTypeEnum;
import com.kuaihuo.ext.controllers.intefaces.IFileParsing;
import com.kuaihuo.ext.mybatis.entitys.AppCountActivityJump;
import com.kuaihuo.ext.services.AppCountActivityJumpService;
import com.kuaihuo.ext.utils.PrintUtil;
import com.kuaihuo.ext.utils.files.FileIOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页面跳转的文件处理工具。主要处理文件类型：{@link com.kuaihuo.ext.controllers.enems.CountTypeEnum#ACTIVITY_JUMP}
 * 注：
 * 关于自动注入失效问题参考：https://blog.csdn.net/wcy18818429914/article/details/106858974/
 */
public class ActivityJumpFileParsingImpl implements IFileParsing {

    //    @Autowired //自己new出来的。没有委托给容器。所以通过工具类来从SpringBoot容器中获取Bean
    private AppCountActivityJumpService iJumpService = BeanUtil.getBean(AppCountActivityJumpService.class);

    @Override
    public void parsingFile(List<File> files) {
        if (files == null || files.isEmpty()) {
            return;
        }
        Map<String, AppCountActivityJump> thisAndToActivityMap = new HashMap<>();
        for (File taskFile : files) {
            try {
                List<String> listLine = FileIOUtils.readFile2List(taskFile, StandardCharsets.UTF_8.name());
                for (String json : listLine) {
                    if (json == null || json.trim().isEmpty()) {
                        continue;
                    }
                    JSONObject jObj = new JSONObject(json);
                    if (!jObj.has("thisActivity") || !jObj.has("toActivity")) {
                        continue; //参数丢失
                    }
                    String thisAct = jObj.getString("thisActivity").trim();
                    String toAct = jObj.getString("toActivity").trim();
                    String key = thisAct + ":" + toAct;
                    if (thisAndToActivityMap.get(key) == null) {
                        AppCountActivityJump item = new AppCountActivityJump();
                        item.setThisActivity(thisAct);
                        item.setToActivity(toAct);
                        item.setTotalCount(1);
                        thisAndToActivityMap.put(key, item);
                    } else {
                        AppCountActivityJump item = thisAndToActivityMap.get(key);
                        item.setTotalCount(item.getTotalCount() + 1);
                        thisAndToActivityMap.put(key, item);
                    }
                }
                //存数据库
                long cur = System.currentTimeMillis();
                for (Map.Entry<String, AppCountActivityJump> stringAppCountActivityJumpEntry : thisAndToActivityMap.entrySet()) {
                    if (iJumpService.insertOrUpdateTotalCount(stringAppCountActivityJumpEntry.getValue()) <= 0) {
                        PrintUtil.println("数据库插入日志信息出现错误:0条数据被更新");
                    }
                }
                PrintUtil.println("更新日志数据入库[" + CountTypeEnum.ACTIVITY_JUMP.name + "]耗时：" + (System.currentTimeMillis() - cur) + "ms");
            } catch (Exception e) {
                PrintUtil.println("数据处理出现异常:" + e);
                e.printStackTrace();
            }finally {
                taskFile.delete();
            }
        }
    }
}
