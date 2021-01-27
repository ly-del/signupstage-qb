package com.cb.signupstage.dto;

import com.cb.signupstage.common.SignDec;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private List<Long> groupIds;

    /**
     * 登陆账号
     */
    private String userName;

    /**
     * 工号
     */
    private String jobNo;

    /**
     * 昵称
     */
    private String nickName;

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
    private SignDec.SexEnum sex;

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

    /**
     * 身份证
     */
    private String  idCard;

    /**
     * 自定义信息
     */
    private String  customInformation;

    /**
     *
     */
    private String  groupName;

    /**
     * 分组id
     */
    private String groupId;
}
