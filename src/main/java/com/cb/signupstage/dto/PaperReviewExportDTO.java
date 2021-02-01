package com.cb.signupstage.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ly
 * @time: 2020/12/24 18:17
 * @description:
 */
@Data
public class PaperReviewExportDTO extends BaseRowModel {

    @ExcelIgnore
    private Long id;

    @ExcelProperty(value = "考试名称", index = 0)
    @ApiModelProperty(value = "考试名称")
    private String testName;
    @ExcelProperty(value = "分组名称", index = 1)
    @ApiModelProperty(value = "分组名称")
    private String paperGroupName;
    @ExcelProperty(value = "论文名称", index = 2)
    @ApiModelProperty(value = "论文名称")
    private String paperName;
    @ExcelProperty(value = "提交人", index = 3)
    @ApiModelProperty(value = "提交人")
    private String userName;
    @ExcelProperty(value = "提交次数", index = 4)
    @ApiModelProperty(value = "提交次数")
    private Integer submitNum;
    @ExcelProperty(value = "审核状态", index = 5)
    @ApiModelProperty(value = "审核状态")
    private String reviewStatus;

    @ExcelProperty(value = "审核老师", index = 6)
    @ApiModelProperty(value = "审核老师")
    private String auditTeacher;
    @ExcelProperty(value = "审核时间", index = 7)
    @ApiModelProperty(value = "审核时间")
    private String createTime;
}
