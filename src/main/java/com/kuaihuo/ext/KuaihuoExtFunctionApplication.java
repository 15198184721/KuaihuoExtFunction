package com.kuaihuo.ext;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.annotation.HandlesTypes;

@HandlesTypes(KuaihuoExtFunctionApplication.class)
@SpringBootApplication
@MapperScan("com.kuaihuo.ext.mybatis.mappers")//使用MapperScan批量扫描所有的Mapper接口
public class KuaihuoExtFunctionApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(KuaihuoExtFunctionApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(KuaihuoExtFunctionApplication.class);
    }
}
