package com.cb.signupstage.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("user_sign_info")
public class

UserSignInfo implements Serializable {

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

    @TableField(value = "deleted",fill = FieldFill.INSERT)
    private Integer deleted;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;



}