package com.cb.signupstage.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.Page;
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
    @TableField("account_id")
    private Long accountId;

    /**
     * 所在分组
     */
    @TableField("group_id")
    private Long groupId;

    /**
     * 报名名称
     */
    @TableField("name")
    private String name;

    /**
     * 描述
     */
    @TableField("`describe`")
    private String describe;

    /**
     * 最大报名人数上限
     */
    @TableField("max_total")
    private Integer maxTotal;

    /**
     * 费用
     */
    @TableField("cost")
    private BigDecimal cost;

    /**
     * 报名开始时间
     */
    @TableField("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime startTime;

    /**
     * 报名结束时间
     */
    @TableField("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime endTime;

    /**
     * 发布状态 1已发布 2未发布
     */
    @TableField("release")
    private Integer release;

    /**
     * 状态(预留)
     */
    @TableField("status")
    private Integer status;

    /**
     * 未开始提示语
     */
    @TableField("unstarted_dec")
    private String unstartedDec;

    /**
     * 结束提示语
     */
    @TableField("complete_dec")
    private String completeDec;

    /**
     * 成功提示语
     */
    @TableField("succeed_dec")
    private String succeedDec;

    /**
     * 失败提示语
     */
    @TableField("fail_dec")
    private String failDec;

    /**
     * 是否跳转到指定页面1不跳转2手动跳转3自动跳转
     */
    @TableField("is_skip")
    private Integer isSkip;

    /**
     * 是否分享
     */
    @TableField("is_share")
    private Integer isShare;

    /**
     * 分享提示语
     */
    @TableField("share_dec")
    private String shareDec;

    /**
     * 是否开启滑动验证
     */
    @TableField("is_open")
    private Integer isOpen;

    /**
     * 报名链接
     */
    @TableField("url")
    private String url;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;

    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime updateTime;


}