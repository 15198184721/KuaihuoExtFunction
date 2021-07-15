package com.kuaihuo.ext;

import com.kuaihuo.ext.mybatis.entitys.User;
import com.kuaihuo.ext.mybatis.mappers.IAppDataCountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试 MyBatisPlus插件的相关测试和功能测试
 */
@SpringBootTest
public class KuaihuoMybatisPlusTest {

    @Autowired
    IAppDataCountMapper appDataCountMapper;

    @Test
    public void testInsert() {
        User entity = new User();
        entity.setUserId("150110");
        entity.setName("小黎");
        entity.setAge(26);
        entity.setTel("15198184721");
        int rows = appDataCountMapper.insert(entity);
        System.out.println("影响行数：" + rows);
    }

}
