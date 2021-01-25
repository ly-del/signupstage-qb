package com.cb.signupstage.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

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
    @NotEmpty(message = "手机号码不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8|9)\\d{9}$", message = "手机号码格式错误")
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
