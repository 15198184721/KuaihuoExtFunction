package com.kuaihuo.ext;

import com.kuaihuo.ext.mybatis.entitys.AppCountActivityJump;
import com.kuaihuo.ext.mybatis.entitys.AppCountUser;
import com.kuaihuo.ext.services.AppCountActivityJumpService;
import com.kuaihuo.ext.services.AppCountUserService;
import com.kuaihuo.ext.utils.PrintUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试 MyBatisPlus插件的相关测试和功能测试
 */
@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
public class KuaihuoMybatisPlusTest {

    @Autowired
    AppCountUserService appUserCountService;

    @Test
    public void testInsert() {
        AppCountUser entity = new AppCountUser();
        entity.setUserId("15");
        entity.setName("小李2");
        int rows = appUserCountService.insertOrUpdate(entity);
        PrintUtil.println("影响行数：" + rows);
    }

    @Test
    public void testInsertList() {
        List<AppCountUser> entitys = new ArrayList();
        for (int i = 0; i < 50; i++) {
            AppCountUser entity = new AppCountUser();
            entity.setUserId("" + (i + 1));
            entity.setName("小黎");
            entitys.add(entity);
        }
        int rows = appUserCountService.insertList(entitys);
        PrintUtil.println("影响行数：" + rows);
    }

    @Test
    public void getTestAppCountData() {
        System.out.println("获取的内容：" + appUserCountService.getUserById("1"));
    }

    @Autowired
    AppCountActivityJumpService appJumpService;

    @Test
    public void testAppCountActivityJumpUpdate() {
        AppCountActivityJump jump = new AppCountActivityJump();
        jump.setThisActivity("1com.a.b.c.Activity0");
        jump.setToActivity("1com.a.b.c.Activity20");
//        jump.setTotalCount(100);
        int rows = appJumpService.insertOrUpdateTotalCount(jump);
        PrintUtil.println("影响行数：" + rows);
    }

    @Test
    public void testAppCountActivityInserList() {
        List<AppCountActivityJump> lis = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            AppCountActivityJump jump = new AppCountActivityJump();
            jump.setThisActivity("com.a.b.c.Activity"+i);
            jump.setToActivity("com.a.b.c.Activity2"+i);
            lis.add(jump);
        }
        int rows = appJumpService.insertList(lis);
        PrintUtil.println("影响行数：" + rows);
    }
}
