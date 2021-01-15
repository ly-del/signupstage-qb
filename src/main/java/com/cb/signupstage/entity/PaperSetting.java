package com.cb.signupstage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 论文分组配置表
 * </p>
 *
 * @author ly
 * @since 2020-12-23
 */
@Data
@TableName("paper_setting")
public class PaperSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建账号id
     */
    @TableField("account_id")
    private Long accountId;

    /**
     * 考试id
     */
    @TableField("test_id")
    private Long testId;

    /**
     * 分组名称
     */
    @TableField("paper_group_name")
    private String paperGroupName;

    /**
     * 分组描述
     */
    @TableField("paper_group_dec")
    private String paperGroupDec;

    /**
     * 审核老师ids
     */
    @TableField("audit_teacher")
    private String auditTeacher;

    /**
     * 状态
     */
    @TableField(value = "deleted",fill = FieldFill.INSERT)
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime updateTime;


}
