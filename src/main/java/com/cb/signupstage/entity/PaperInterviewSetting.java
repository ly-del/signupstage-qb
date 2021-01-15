package com.cb.signupstage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 面试预约时间设置表
 * </p>
 *
 * @author ly
 * @since 2020-12-25
 */
@Data
@TableName("paper_interview_setting")
public class PaperInterviewSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 论文分组id
     */
    @TableField("paper_group_id")
    private Long paperGroupId;

    /**
     * 可预约人数
     */
    @TableField("can_booked_number")
    private Integer canBookedNumber;

    /**
     * 已预约人数
     */
    @TableField("booked_number")
    private Integer bookedNumber;

    /**
     * 预约日期
     */
    @TableField("booked_date")
    private String bookedDate;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private String startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private String endTime;

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
