package com.cb.signupstage.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author: ly
 * @time: 2021/1/28 16:31
 * @description:
 */
@Data
public class PaperReviewImportVo extends BaseRowModel{


    @ApiModelProperty(value = "审核时间")
    @ExcelProperty(value = "论文名称",index = 0)
    private String paperName;

    @ExcelProperty(value = "状态",index = 1)
    @ApiModelProperty(value = "审核时间")
    private String repeatStatus;

    private String dec;

}
