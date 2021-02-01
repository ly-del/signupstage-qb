package com.cb.signupstage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.cb.signupstage.common.SignDec;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 论文上传记录表
 * </p>
 *
 * @author ly
 * @since 2020-12-25
 */
@Data
@TableName("paper_upload_record")
public class PaperUploadRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 论文分组id
     */
    @TableField("paper_group_id")
    private Long paperGroupId;

    /**
     * 论文名称
     */
    @TableField("paper_name")
    private String paperName;

    /**
     * 论文url
     */
    @TableField("paper_url")
    private String paperUrl;

    /**
     * 删除标识
     */
    @TableField(value = "deleted",fill = FieldFill.INSERT)
    private Integer deleted;

    /**
     * 状态 0 待审核 1 审核通过 2 审核不通过
     */
    @TableField("review_status")
    private SignDec.paperReviewStatus reviewStatus;

    /**
     * 面试成绩
     */
    @TableField("score")
    private BigDecimal score;

    /**
     * 创建账号id
     */
    @TableField("user_id")
    private Long userId;


    /**
     *
     */
    @TableField("repeat_status")
    private Integer repeatStatus;

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
