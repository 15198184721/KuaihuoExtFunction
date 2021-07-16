package com.kuaihuo.ext.components.sqlinject;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.kuaihuo.ext.components.sqlinject.injects.MybatisSqlInject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * mybatis Sql相关的组件配置
 */
@Component
public class MyBatisExSqlInjectorComponent {

    /**
     * 扩展的数据方法注入，扩展 MyBatis-plus 默认的 Mapper的基础映射(BaseMapper)数据操作方法
     * 扩展在BaseMapper中没有的一些数据看操作方法注入提供者。将注入自定义提供给容器
     *
     * @return
     */
    @Bean
    public ISqlInjector extSqlInjector() {
        return new MybatisSqlInject();
    }

}
