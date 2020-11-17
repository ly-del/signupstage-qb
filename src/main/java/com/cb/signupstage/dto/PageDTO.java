package com.cb.signupstage.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wwn
 * @create_time 2020/9/3
 * @description 分页转化类
 */
@Data
public class PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 当前页数
     */
    private Integer jumpPage;

}
