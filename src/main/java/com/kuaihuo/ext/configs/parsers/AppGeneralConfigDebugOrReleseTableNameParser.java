package com.kuaihuo.ext.configs.parsers;

import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;

/**
 * 应用通用配置表的动态表名处理器
 * debug和relese模式区分的动态表处理器。按照debug和relese区分多租户模式
 * 实际就是动态表明
 */
public class AppGeneralConfigDebugOrReleseTableNameParser implements TableNameHandler {

    //使用ThreadLocal防止多线程相互影响
    private static ThreadLocal<String> model = new ThreadLocal<String>();

    public static void setTableModel(String modelValue) {
        model.set(modelValue);
    }

    public AppGeneralConfigDebugOrReleseTableNameParser() {
    }

    @Override
    public String dynamicTableName(String sql, String tableName) {
        String tMod = model.get();
        if (tMod == null) {
            return tableName; //不更改。保持原始表名称
        }
        model.set(null); //清除。防止线程复用导致问题
        return tableName + tMod;
    }
}
