package com.cb.signupstage.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 报名信息表
 * </p>
 *
 * @author MyBatisPlusGenerater
 * @since 2020-11-13
 */

@Data
@TableName("sign_info")
public class SignInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建账号id
     */
    @ApiModelProperty(value = "创建账号id")
    @TableField("account_id")
    private Long accountId;

    /**
     * 所在分组
     */
    @ApiModelProperty(value = "所在分组")
    @TableField("group_id")
    private Long groupId;

    /**
     * 报名名称
     */
    @ApiModelProperty(value = "报名名称")
    @TableField("name")
    private String name;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @TableField("`describe`")
    private String describe;

    /**
     * 最大报名人数上限
     */
    @ApiModelProperty(value = "最大报名人数上限")
    @TableField("max_total")
    private Integer maxTotal;

    /**
     * 费用
     */
    @ApiModelProperty(value = "费用")
    @TableField("cost")
    private BigDecimal cost;

    /**
     * 报名开始时间
     */
    @ApiModelProperty(value = "报名开始时间")
    @TableField("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime startTime;

    /**
     * 报名结束时间
     */
    @ApiModelProperty(value = "报名结束时间")
    @TableField("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime endTime;

    /**
     * 发布状态 1已发布 2未发布
     */
    @ApiModelProperty(value = "发布状态 1已发布 2未发布")
    @TableField("is_release")
    private Integer isRelease;

    /**
     * 状态(预留)
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "deleted",fill = FieldFill.INSERT)
    private Integer deleted;

    /**
     * 未开始提示语
     */
    @ApiModelProperty(value = "未开始提示语")
    @TableField("unstarted_dec")
    private String unstartedDec;

    /**
     * 结束提示语
     */
    @ApiModelProperty(value = "结束提示语")
    @TableField("complete_dec")
    private String completeDec;

    /**
     * 成功提示语
     */
    @ApiModelProperty(value = "成功提示语")
    @TableField("succeed_dec")
    private String succeedDec;

    /**
     * 失败提示语
     */
    @ApiModelProperty(value = "失败提示语")
    @TableField("fail_dec")
    private String failDec;

    /**
     * 是否跳转到指定页面1不跳转2手动跳转3自动跳转
     */
    @ApiModelProperty(value = "是否跳转到指定页面1不跳转2手动跳转3自动跳转")
    @TableField("is_skip")
    private Integer isSkip;

    @ApiModelProperty(value = "跳转页面")
    @TableField("skip_html")
    private Integer skipHtml;

    @ApiModelProperty(value = "跳转页面")
    @TableField("button_name")
    private String buttonName;

    @ApiModelProperty(value = "试卷id")
    @TableField("exam_id")
    private Long examId;

    @ApiModelProperty(value = "跳转页面")
    @TableField("skip_url")
    private String skipUrl;

    /**
     * 是否分享
     */
    @ApiModelProperty(value = "是否分享(1是2否)")
    @TableField("is_share")
    private Integer isShare;

    /**
     * 分享提示语
     */
    @ApiModelProperty(value = "分享提示语")
    @TableField("share_dec")
    private String shareDec;

    /**
     * 是否开启滑动验证
     */
    @ApiModelProperty(value = "是否开启滑动验证")
    @TableField("is_open")
    private Integer isOpen;

    /**
     * 报名链接
     */
    @ApiModelProperty(value = "报名链接")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "背景图片url")
    @TableField("image")
    private String image;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;


    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime updateTime;


}
