package com.kuaihuo.ext.utils;

/**
 * 输出工具类
 */
public class PrintUtil {

    /**
     * 输入日志的方法
     * @param msg
     */
    public static void println(String msg){
        println("通用日志",msg);
    }

    /**
     * 输入日志的方法
     * @param tag
     * @param msg
     */
    public static void println(String tag,String msg){
        System.out.println(tag+"--->"+msg);
    }
}
