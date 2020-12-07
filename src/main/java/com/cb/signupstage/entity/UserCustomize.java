package com.cb.signupstage.entity;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
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
     * 自定义种类状态  1 正常 2 删除
     */
    @TableField("status")
    private Integer status;

    /**
     *创建时间
     */

    @TableField("create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    /**
     *更新时间
     */
    @TableField("update_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;




}