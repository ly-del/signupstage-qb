package com.cb.signupstage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ly
 * @time: 2020/11/27 8:44
 * @description:
 */
@Data
public class SignInfoPageSearchVo {
    @ApiModelProperty(value = "第几页")
    private Integer jumpPage;
    @ApiModelProperty(value = "每页几条")
    private Integer pageSize;
}
