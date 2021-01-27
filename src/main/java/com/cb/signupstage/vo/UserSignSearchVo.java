package com.cb.signupstage.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: ly
 * @time: 2020/11/17 16:36
 * @description:
 */
@Data
public class UserSignSearchVo extends BaseRowModel {


    @ExcelIgnore
    @ApiModelProperty(value = "报名信息id")
    private String signId;

    @ExcelProperty(value = "姓名", index = 0)
    @ApiModelProperty(value = "姓名")
    private String userName;

    @ExcelProperty(value = "电话", index = 1)
    @ApiModelProperty(value = "电话")
    private String mobile;

    @ExcelProperty(value = "性别", index = 2)
    @ApiModelProperty(value = "性别")
    private String sex;

    @ExcelProperty(value = "身份证", index = 3)
    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ExcelProperty(value = "职位", index = 4)
    @ApiModelProperty(value = "职位")
    private String positionName;

    @ExcelProperty(value = "部门", index = 5)
    @ApiModelProperty(value = "部门")
    private String deptName;


    @ExcelIgnore
    @ApiModelProperty(value = "当前页" )
    private Integer pageNum;


    @ExcelIgnore
    @ApiModelProperty(value = "每页显示的条数")
    private Integer pageSize;

    @ExcelProperty(value = "报名时间", index =6)
    @ApiModelProperty(value = "报名时间")
    private Date creatTime;
}
