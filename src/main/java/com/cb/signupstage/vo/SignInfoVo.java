package com.cb.signupstage.vo;

import com.cb.signupstage.entity.SignInfo;
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

    private BigDecimal costTotal;

    private Integer count;

    private String groupName;

}
