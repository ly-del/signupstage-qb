package com.cb.signupstage.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ly
 * @time: 2020/11/23 13:25
 * @description:
 */
@Data
public class CustomizeListDTO {

     @ApiModelProperty(value = "主键id")
     private Long id;
     @ApiModelProperty(value = "名称")
     private String  name;
     @ApiModelProperty(value = "是否自定义 1固定属性 2自定义属性")
     private Integer type;
}
