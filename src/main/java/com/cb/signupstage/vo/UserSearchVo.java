package com.cb.signupstage.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: ly
 * @time: 2020/11/24 9:24
 * @description:
 */
@Data
public class UserSearchVo {


    @ApiModelProperty(value = "报名信息id")
    private Long signId;

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
    private Integer JumpPage;


    @ApiModelProperty(value = "每页显示的条数")
    private Integer pageSize;

    @ApiModelProperty(value = "报名时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "缴费状态")
    private Integer costStatus;
}
