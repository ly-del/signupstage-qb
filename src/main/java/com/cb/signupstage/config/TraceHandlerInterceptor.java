package com.cb.signupstage.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wwn
 * @create_time 2020/10/30
 * @description 使用拦截器将将tracId加到request上，便于日志追踪
 */
public class TraceHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        com.cb.signupstage.config.TraceContextHolder.init();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        com.cb.signupstage.config.TraceContextHolder.clear();
    }
}
