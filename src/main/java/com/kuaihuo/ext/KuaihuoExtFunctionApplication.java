package com.kuaihuo.ext;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.annotation.HandlesTypes;

@HandlesTypes(ServletInitializer.class)
@SpringBootApplication
@MapperScan("com.kuaihuo.ext.mybatis.mappers")//使用MapperScan批量扫描所有的Mapper接口；
public class KuaihuoExtFunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(KuaihuoExtFunctionApplication.class, args);
    }

}
