package com.kuaihuo.ext.components.inits;

import com.kuaihuo.ext.utils.PrintUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * SpringBoot 启动成功后的回调,初始化本地、内存数据之类的
 */
@Component
@Order(1)
public class InitializationCache implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        PrintUtil.println("框架已启动成功了");
    }
}
