package com.cb.signupstage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
@Setter
@Getter
@ToString
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
     * 手机
     */
    @TableField("mobile")
    private String mobile;

    @TableField("name")
    private String name;

    /**
     * 自定义字段
     */
    @TableField("custom_information")
    private String customInformation;

    /**
     * 报名id
     */
    @TableField("sign_info_id")
    private Long signInfoId;

    /**
     * 状态(预留)
     */
    @TableField("status")
    private Integer status;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("update_user")
    private Long updateUser;


}
