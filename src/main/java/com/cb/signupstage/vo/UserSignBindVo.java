package com.cb.signupstage.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
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
    @NotEmpty(message = "姓名不能为空")
    @Length(max = 30, min = 2,message = "姓名长度限制2~30字符")
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
    private String idCard;

    /**
     * 描述
     */

    private Object customInformation;

}
