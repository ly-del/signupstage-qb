package com.cb.signupstage.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: ly
 * @time: 2020/12/30 18:11
 * @description:
 */
public class PaperReviewDTO {



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
        @JsonFormat(shape = JsonFormat.Shape.OBJECT)
        @TableField("review_status")
        private Integer reviewStatus;


        /**
         * 审核意见
         */
        @TableField("review_dec")
        private String reviewDec;

        /**
         * 审核人
         */
        @TableField("reviewer")
        private Long reviewer;

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
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        private LocalDateTime createTime;

        /**
         * 更新时间
         */
        @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        private LocalDateTime updateTime;




}
