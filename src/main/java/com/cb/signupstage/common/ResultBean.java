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
public class ResultBean implements Serializable{
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
    private Object data;

    //返回成功
    public static ResultBean success(){
        ResultBean resultBean = new ResultBean();
        resultBean.setStatusCode(200);
        resultBean.setResult(true);
        return resultBean;
    }

    public static ResultBean success(String Msg){
        ResultBean resultBean = new ResultBean();
        resultBean.setStatusCode(200);
        resultBean.setResult(true);
        resultBean.setFailMsg(Msg);
        return resultBean;
    }

    //返回成功
    public static ResultBean success(Object data,String Msg){
        ResultBean resultBean = new ResultBean();
        resultBean.setStatusCode(200);
        resultBean.setResult(true);
        resultBean.setFailMsg(Msg);
        resultBean.setData(data);
        return resultBean;
    }
    //返回失败
    public static ResultBean failure(String Msg){
        ResultBean resultBean = new ResultBean();
        resultBean.setStatusCode(500);
        resultBean.setResult(false);
        resultBean.setFailMsg(Msg);
        return resultBean;
    }

    public static ResultBean result(boolean result ,String Msg){
        ResultBean resultBean = new ResultBean();
        resultBean.setResult(result);
        resultBean.setFailMsg(Msg);
        if (result){
            resultBean.setStatusCode(200);
        }else{
            resultBean.setStatusCode(500);
        }
        return resultBean;
    }

}
