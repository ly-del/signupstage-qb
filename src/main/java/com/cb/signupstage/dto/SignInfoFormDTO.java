package com.cb.signupstage.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: ly
 * @time: 2020/11/24 9:47
 * @description:
 */
@Data
public class SignInfoFormDTO {


    private Long id;
    /**
     * 排序
     */

    private Integer sort;

    /**
     * 提示信息
     */

    private String information;

   private String name;

   private Integer deleted;

   private Long customizeId;

   private String field;
}
