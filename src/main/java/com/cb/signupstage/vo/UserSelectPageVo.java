package com.cb.signupstage.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ly
 * @time: 2020/11/23 18:13
 * @description:   用户列表查询
 */
@Data
public class UserSelectPageVo {




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
    private Integer jumpPage;


    @ApiModelProperty(value = "每页显示的条数")
    private Integer pageSize;

    @ApiModelProperty(value = "分组id")
    private Long groupId;
}
