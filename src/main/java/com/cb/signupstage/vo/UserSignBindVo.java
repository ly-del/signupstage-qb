package com.cb.signupstage.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author: ly
 * @time: 2020/11/16 13:45
 * @description: 用户报名传参vo
 */
@Data
public class UserSignBindVo implements Serializable {

    private static final long serialVersionUID = -6927414111393716494L;

    /**
     * 报名id
     */
    private Long SignInfoId;

    /**
     * 登陆账号
     */
    private String userName;

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

    private Object customInformation;

}
