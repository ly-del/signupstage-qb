package com.cb.signupstage.entity;



import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("user_customize")
public class UserCustomize implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     *自定义名称
     */
    @TableField("name")
    private String name;

    /**
     *是否自定义  默认 1 是
     */
    @TableField("type")
    private Integer type;

    @TableField("account_id")
    private Long accountId;

    @TableField("is_must")
    private Integer isMust;


    @TableField("field")
    private String field;
    /**
     * 自定义种类状态  0 正常 1 删除
     */
    @TableField(value = "deleted",fill = FieldFill.INSERT)
    private Integer deleted;

    /**
     *创建时间
     */

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;

    /**
     *更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;




}