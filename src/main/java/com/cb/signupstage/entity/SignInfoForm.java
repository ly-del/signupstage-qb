package com.cb.signupstage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 报名页面设置表
 * </p>
 *
 * @author ly
 * @since 2020-11-16
 */
@Data
@TableName("sign_info_form")
public class SignInfoForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建账号id
     */
    @TableField("account_id")
    private Long accountId;

    /**
     * 自定义字段id
     */
    @TableField("customize_id")
    private Long customizeId;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;



    /**
     * 报名id
     */
    @TableField("sign_info_id")
    private Long signInfoId;


    /**
     * 提示信息
     */
    @TableField("information")
    private String information;

    /**
     * 状态
     */
    @TableField(value = "deleted",fill = FieldFill.INSERT)
    private Integer deleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField(value="update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("update_user")
    private Long updateUser;


}
