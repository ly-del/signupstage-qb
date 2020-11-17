package com.cb.signupstage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("user_sign_info")
public class UserSignInfo implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("account_id")
    private Long accountId;

    @TableField("user_id")
    private Long userId;

    @TableField("sign_id")
    private Long signId;

    @TableField("cost_status")
    private Integer costStatus;

    @TableField("cost")
    private BigDecimal cost;

    @TableField("cost_time")
    private Date costTime;

    @TableField("return_cost")
    private BigDecimal returnCost;

    @TableField("return_time")
    private Date returnTime;

    @TableField("status")
    private Integer status;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;



}