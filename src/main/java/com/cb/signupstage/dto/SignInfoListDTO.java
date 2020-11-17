package com.cb.signupstage.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: ly
 * @time: 2020/11/16 8:38
 * @description: 分页数据list
 */
@Getter
@Setter
@ToString
public class SignInfoListDTO {

    private Long id;

    /**
     * 创建账号id
     */
    private Long accountId;

    /**
     * 所在分组
     */
    private Long groupId;

    private String groupName;

    /**
     * 报名名称
     */
    private String name;

    /**
     * 描述
     */
    private String describe;

    /**
     * 最大报名人数上限
     */
    private Integer maxTotal;

    /**
     * 费用
     */
    private BigDecimal cost;

    /**
     * 报名开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    /**
     * 报名结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    /**
     * 利润
     */
    private BigDecimal profit;

    /**
     * 已报名人数
     */
    private Integer signNum;

}
