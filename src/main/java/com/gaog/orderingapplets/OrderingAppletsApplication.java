package com.gaog.orderingapplets;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 功能描述: 订购小程序应用程序
 *
 * @CLASSNAME： OrderingAppletsApplication
 * @VERSION: 1.0.0
 * @Date：2024/12/09
 * @Author： ZSJ
 */
@Slf4j

@SpringBootApplication
@MapperScan(basePackages = {
        "com.gaog.orderingapplets.restaurant.config",
        "com.gaog.orderingapplets.restaurant.mapper"
})
public class OrderingAppletsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(OrderingAppletsApplication.class, args);
        Environment environment = configurableApplicationContext.getBean(Environment.class);
        // 项目的根环境
        String path = "http://localhost:" + environment.getProperty("server.port") + environment.getProperty("server.servlet.context-path");
        log.info(" ============> 项目地址：{}", path);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(OrderingAppletsApplication.class);
    }

}
