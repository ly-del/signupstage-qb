package com.cb.signupstage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wwn
 * @create_time 2020/10/30
 * @description 注册拦截器的配置
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 链路追踪
        registry.addInterceptor(new com.cb.signupstage.config.TraceHandlerInterceptor());
    }
}
