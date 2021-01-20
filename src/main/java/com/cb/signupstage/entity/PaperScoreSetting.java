package com.cb.signupstage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 面试及格成绩设置表
 * </p>
 *
 * @author ly
 * @since 2020-12-29
 */
@Data
@TableName("paper_score_setting")
public class PaperScoreSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 考试id
     */
    @TableField("test_id")
    private Long testId;

    /**
     * 及格分数
     */
    @TableField("score")
    private Integer score;

    @TableField("source")
    private Integer source;

    /**
     * 删除标识
     */
    @TableField(value = "deleted",fill = FieldFill.INSERT)
    private Integer deleted;

    /**
     * 创建账号id
     */
    @TableField("account_id")
    private Long accountId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
