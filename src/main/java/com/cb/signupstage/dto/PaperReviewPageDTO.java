package com.cb.signupstage.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cb.signupstage.common.SignDec;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ly
 * @time: 2020/12/31 11:15
 * @description:
 */
@Data
public class PaperReviewPageDTO {

    @ExcelIgnore
    private Long id;


    @ApiModelProperty(value = "考试名称")
    private String testName;

    @ApiModelProperty(value = "分组名称")
    private String paperGroupName;

    @ApiModelProperty(value = "论文名称")
    private String paperName;

    @ApiModelProperty(value = "提交人")
    private String userName;

    @ApiModelProperty(value = "提交次数")
    private Integer submitNum;

    @ApiModelProperty(value = "审核状态")
    private SignDec.paperReviewStatus reviewStatus;


    @ApiModelProperty(value = "审核老师")
    private String auditTeacher;

    @ApiModelProperty(value = "审核时间")
    private String createTime;

    @ApiModelProperty(value = "考生id")
    private Long userId;
}
