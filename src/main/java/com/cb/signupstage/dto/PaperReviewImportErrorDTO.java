package com.cb.signupstage.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ly
 * @time: 2021/2/1 13:17
 * @description:
 */
@Data
public class PaperReviewImportErrorDTO extends BaseRowModel {

    @ApiModelProperty(value = "审核时间")
    @ExcelProperty(value = "论文名称",index = 0)
    private String paperName;

    @ExcelProperty(value = "状态",index = 1)
    @ApiModelProperty(value = "审核时间")
    private String repeatStatus;

    @ExcelProperty(value = "失败说明",index = 2)
    @ApiModelProperty(value = "失败说明")
    private String dec;
}
