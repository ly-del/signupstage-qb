package com.cb.signupstage.config;

import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author wwn
 * @create_time 2020/10/30
 * @description 生成tracId
 */
public class TraceContextHolder {
    private static final ThreadLocal<String> TRACE_INFO= ThreadLocal.withInitial(() -> UUID.randomUUID().toString().replaceAll("-",""));

    public static void init(){
        if (StringUtils.isEmpty(TRACE_INFO.get())){
            TRACE_INFO.set(UUID.randomUUID().toString().replaceAll("-",""));
        }
    }

    public static String getTrace(){
        return  TRACE_INFO.get();
    }

    public static void  clear(){
        TRACE_INFO.remove();
    }
}
