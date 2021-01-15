package com.cb.signupstage.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ly
 * @time: 2020/12/29 9:31
 * @description:
 */
@Data
public class PaperInterviewSettingExportDTO extends BaseRowModel {

    @ExcelIgnore
    @ApiModelProperty(value = "id")
    private Long id;

    @ExcelProperty(value = "考试名称", index = 0)
    @ApiModelProperty(value = "考试名称")
    private String testName;

    @ExcelProperty(value = "论文分组", index = 1)
    @ApiModelProperty(value = "论文分组")
    private String paperGroupName;
    @ExcelProperty(value = "论文名称", index = 2)
    @ApiModelProperty(value = "论文名称")
    private String paperName;
    @ExcelProperty(value = "考生姓名", index = 3)
    @ApiModelProperty(value = "考生姓名")
    private String userName;
    @ExcelProperty(value = "评分人", index = 4)
    @ApiModelProperty(value = "评分人")
    private String auditTeacher;

    @ExcelProperty(value = "分数", index = 5)
    @ApiModelProperty(value = "分数")
    private String scoreString;
    @ExcelProperty(value = "最终成绩", index = 6)
    @ApiModelProperty(value = "最终成绩")
    private String score;
    @ExcelProperty(value = "状态", index = 7)
    @ApiModelProperty(value = "状态")
    private String interviewResult;
    @ExcelProperty(value = "答辩时间", index = 8)
    @ApiModelProperty(value = "答辩时间")
    private String createTime;

}
