package com.kuaihuo.ext.configs;

import com.kuaihuo.ext.KuaihuoExtFunctionApplication;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 部署war包,此类为Servlt的初始化配置类
 *
 * KuaihuoExtFunctionApplication.class 是启动类。
 */
public class ServletInitializerConfig extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(KuaihuoExtFunctionApplication.class);
	}

}
