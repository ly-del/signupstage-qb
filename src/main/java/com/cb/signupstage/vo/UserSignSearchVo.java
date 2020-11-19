package com.cb.signupstage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: ly
 * @time: 2020/11/17 16:36
 * @description:
 */
@Data
public class UserSignSearchVo {

    @ApiModelProperty(value = "报名信息id")
    private String signId;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "电话")
    private String mobile;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ApiModelProperty(value = "职位")
    private String positionName;

    @ApiModelProperty(value = "部门")
    private String deptName;

    @ApiModelProperty(value = "当前页" )
    private Integer pageNum;

    @ApiModelProperty(value = "每页显示的条数")
    private Integer pageSize;

    @ApiModelProperty(value = "报名时间")
    private Date creatTime;
}
