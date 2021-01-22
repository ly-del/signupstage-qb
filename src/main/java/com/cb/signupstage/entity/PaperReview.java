package com.cb.signupstage.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.cb.signupstage.common.SignDec;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 论文审核记录表
 * </p>
 *
 * @author ly
 * @since 2020-12-25
 */
@Data
@TableName("paper_review")
public class PaperReview implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 论文id
     */
    @TableField("paper_id")
    private Long paperId;

    /**
     * 审核状态
     */
    @TableField("review_status")
    private SignDec.paperReviewStatus reviewStatus;


    /**
     * 审核意见
     */
    @TableField("review_dec")
    private String reviewDec;

    /**
     * 创建人
     */
    @TableField("account_id")
    private Long accountId;

    @TableField("user_id")
    private Long userId;

    /**
     * 删除标识
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
