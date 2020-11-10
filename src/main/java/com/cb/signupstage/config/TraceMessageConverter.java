package com.cb.signupstage.config;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author wwn
 * @create_time 2020/10/30
 * @description %trace 占位符的解析器，作用是获取当前TraceId
 */
public class TraceMessageConverter extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        return com.cb.signupstage.config.TraceContextHolder.getTrace();
    }
}