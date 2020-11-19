package com.cb.signupstage.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author: ly
 * @time: 2020/11/16 13:45
 * @description: 用户报名传参vo
 */
@Getter
@Setter
public class UserSignBindVo {

    /**
     * 报名id
     */
    private Long SignInfoId;

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
    private String faceUrl;

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
    private String positionName;

    /**
     * 部门
     */
    private String deptName;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 描述
     */
    private String customInformation;

}
