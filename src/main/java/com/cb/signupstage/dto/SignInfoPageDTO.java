package com.cb.signupstage.dto;

import com.cb.signupstage.vo.SignInfoVo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: ly
 * @time: 2020/11/16 8:36
 * @description:   报名管理 分页列表
 */
@Data
public class SignInfoPageDTO {

    private Integer week;

    private Integer month;

    private BigDecimal cost;

    PagedResult<SignInfoVo> pagedResult;

}
