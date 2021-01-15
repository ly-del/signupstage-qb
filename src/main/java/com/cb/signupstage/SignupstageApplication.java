package com.cb.signupstage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
@SpringBootApplication
@MapperScan("com.cb.signupstage.mapper")
@EnableCaching
@EnableScheduling
@EnableAsync
public class SignupstageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignupstageApplication.class, args);
    }

}
