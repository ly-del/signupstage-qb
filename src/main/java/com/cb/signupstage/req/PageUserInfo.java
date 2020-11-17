package com.cb.signupstage.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: ly
 * @time: 2020/11/12 8:53
 * @description:
 */
@Data
public class PageUserInfo implements Serializable {
    private static final long serialVersionUID = -4797323667775561370L;

    /**
     * 分组id
     */
    private Long groupId;

    /**
     * 状态(1-草稿，2-未开始，3-考试中，4-已考完)
     */
    private Integer status;


    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 要去跳转的页数
     */
    private Integer jumpPage;

    /**
     * 姓名
     */
    private String username;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 手机
     */
    private Integer mobile;
    /**
     * 身份证
     */
    private Integer idcard;

    /**
     * 部门
     */
    private String deptName;

    /**
     * 职位
     */
    private Integer positionName;

}
