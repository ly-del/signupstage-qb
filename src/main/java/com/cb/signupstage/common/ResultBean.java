package com.cb.signupstage.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: ly
 * @time: 2020/11/10 17:52
 * @description:
 */
@Data
@Builder
public class ResultBean<T> implements Serializable{
    private static final long serialVersionUID = 8962001572545196062L;

    /**
     * 响应码，用于后台定位问题
     */
    private Integer statusCode;

    /**
     * 失败的消息
     */
    private String failMsg;

    /**
     * 接口执行结果
     */
    private Boolean result;

    /**
     * 接口返回数据
     */
    private T data;
}
