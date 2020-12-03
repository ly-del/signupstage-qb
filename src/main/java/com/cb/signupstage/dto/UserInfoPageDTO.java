package com.cb.signupstage.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: ly
 * @time: 2020/11/12 14:23
 * @description:
 */
@Data
public class UserInfoPageDTO {
    private Long id;

    /**
     * 分组id
     */
    private Long groupId;

    /**
     * 登陆账号
     */
    private String username;

    /**
     * 工号
     */
    private String jobno;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 地址
     */
    private String address;

    /**
     * 职位
     */
    private String  positionName;

    /**
     * 部门
     */
    private String  deptName;
    private String  idcard;
    private String  customInformation;

    /**
     *
     */
    private String  groupName;
}
