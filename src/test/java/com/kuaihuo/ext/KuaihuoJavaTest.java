package com.kuaihuo.ext;

import com.kuaihuo.ext.services.AppCountUserService;
import com.kuaihuo.ext.utils.PrintUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

/**
 * 测试java方法单纯的
 */
public class KuaihuoJavaTest {


    @Test
    public void testJava() {
        StringBuffer sb1 = new StringBuffer("sb 第1个");
        StringBuffer sb2 = new StringBuffer("sb2 第2个");
        PrintUtil.println(sb1 + "," + sb2);
        testSwap(sb1, sb2);
        HashMap
        PrintUtil.println(sb1 + "," + sb2);
    }

    private void testSwap(StringBuffer sb1, StringBuffer sb2) {
        sb1 = new StringBuffer("sb 第3个");
        sb2 = new StringBuffer("sb 第4个");
    }

}
