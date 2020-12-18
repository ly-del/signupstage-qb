package com.cb.signupstage.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cb.signupstage.vo.SignInfoVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: ly
 * @time: 2020/11/16 8:36
 * @description:   报名管理 分页列表
 */
@Data
public class SignInfoPageDTO {

    @ApiModelProperty(value = "本周报名人数")
    private Integer week;

    @ApiModelProperty(value = "本月报名人数")
    private Integer month;

    @ApiModelProperty(value = "费用")
    private BigDecimal cost;

    IPage<SignInfoVo> pagedResult;

}
