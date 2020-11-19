package com.cb.signupstage.vo;

import com.cb.signupstage.entity.SignInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author: ly
 * @time: 2020/11/13 13:42
 * @description:
 */
@Data
public class SignInfoVo extends SignInfo {

    private String ids;

    @ApiModelProperty(value = "总收益")
    private BigDecimal costTotal;
    @ApiModelProperty(value = "已报名人数")
    private Integer count;
    @ApiModelProperty(value = "分组名称")
    private String groupName;

}
