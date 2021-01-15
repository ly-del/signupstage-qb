package com.cb.signupstage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.description.field.FieldList;

import java.io.Serializable;

/**
 * <p>
 * 用户分组关系表
 * </p>
 *
 * @author ly
 * @since 2020-11-13
 */
@Data
@TableName("user_group_bind")
public class UserGroupBind implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建账号id
     */
    @TableField("account_id")
    private Long accountId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 分组id
     */
    @TableField("group_id")
    private Long groupId;

    /**
     * 状态 0正常 1删除
     */
    @TableField(value = "deleted",fill = FieldFill.INSERT)
    private Integer deleted;

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
