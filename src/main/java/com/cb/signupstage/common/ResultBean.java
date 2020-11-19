package com.cb.signupstage.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author: ly
 * @time: 2020/11/10 17:52
 * @description:
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ApiModel(value = "响应信息主体")
public class ResultBean<T> implements Serializable{
    private static final long serialVersionUID = 8962001572545196062L;

    /**
     * 响应码，用于后台定位问题
     */
    @ApiModelProperty(value = "响应码")
    private Integer statusCode;

    /**
     * 失败的消息
     */
    @ApiModelProperty(value = "失败的消息")
    private String failMsg;

    /**
     * 接口执行结果
     */
    @ApiModelProperty(value = "接口执行结果")
    private Boolean result;

    /**
     * 接口返回数据
     */
    @ApiModelProperty(value = "接口返回数据")
    private T data;
}
