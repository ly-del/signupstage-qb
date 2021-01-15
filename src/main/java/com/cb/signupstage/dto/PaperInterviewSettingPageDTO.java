package com.cb.signupstage.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cb.signupstage.common.SignDec;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ly
 * @time: 2020/12/31 11:04
 * @description:
 */
@Data
public class PaperInterviewSettingPageDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "考试名称")
    private String testName;

    @ApiModelProperty(value = "论文分组")
    private String paperGroupName;

    @ApiModelProperty(value = "论文名称")
    private String paperName;

    @ApiModelProperty(value = "考生姓名")
    private String userName;

    @ApiModelProperty(value = "评分人")
    private String auditTeacher;


    @ApiModelProperty(value = "分数")
    private String scoreString;

    @ApiModelProperty(value = "最终成绩")
    private String score;

    @ApiModelProperty(value = "状态")
    private String interviewResult;

    @ApiModelProperty(value = "答辩时间")
    private String interviewTime;

}
