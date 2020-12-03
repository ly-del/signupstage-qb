package com.cb.signupstage.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: ly
 * @time: 2020/11/23 14:05
 * @description:
 */
@Data
public class UserGroupCreateVo {


    @ApiModelProperty(value = "总收益")
    private String groupName;

    @ApiModelProperty(value = "总收益")
    private Long parentId;

}
