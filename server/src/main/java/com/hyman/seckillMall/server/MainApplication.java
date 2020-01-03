package com.hyman.seckillMall.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: penghuang
 * @Date: 2019/10/18 23:51
 * @Version 1.0
 */
@SpringBootApplication
@ImportResource(value={"classpath:spring/spring-jdbc.xml"})
@MapperScan(basePackages = "com.hyman.seckillMall.model.mapper")
@EnableScheduling
public class MainApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MainApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}