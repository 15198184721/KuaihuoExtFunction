package com.kuaihuo.ext;

import com.kuaihuo.ext.controllers.enems.AppGeneralConfigEnum;
import com.kuaihuo.ext.mybatis.entitys.AppCountActivityJump;
import com.kuaihuo.ext.mybatis.entitys.AppCountUser;
import com.kuaihuo.ext.mybatis.entitys.AppGeneralConfig;
import com.kuaihuo.ext.mybatis.mappers.IAppGeneralConfigMapper;
import com.kuaihuo.ext.services.AppCountActivityJumpService;
import com.kuaihuo.ext.services.AppCountUserService;
import com.kuaihuo.ext.services.AppGeneralConfigService;
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
}
