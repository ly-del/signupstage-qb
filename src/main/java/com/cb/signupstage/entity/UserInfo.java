package com.cb.signupstage.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.cb.signupstage.common.SignDec;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("user_info")
public class UserInfo implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分组id
     */
    @TableField("face_url")
    private String faceUrl;

    /**
     * 登陆账号
     */
    @TableField("userName")
    private String userName;

    /**
     * 工号
     */
    @TableField("jobNo")
    private String jobNo;

    /**
     * 昵称
     */
    @TableField("nickName")
    private String nickName;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 性别
     */
    @TableField("sex")
    private SignDec.SexEnum sex;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 城市
     */
    @TableField("cityString")
    private String cityString;

    /**
     * '用户类型:platform-平台 isp-服务提供商 dev-自研开发者'
     */
    @TableField("userType")
    private String userType;

    /**
     * 入职时间
     */
    @TableField("hireDate")
    private String hireDate;

    /**
     * 职位
     */
    @TableField("positionName")
    private String positionName;

    /**
     * 所属部门
     */
    @TableField("deptName")
    private String deptName;
    /**
     * 企业ID
     */
    @TableField("companyId")
    private Long companyId;

    /**
     * 注册IP
     */
    @TableField("registerIp")
    private String registerIp;

    /**
     * 用户状态0-禁用 1-启用 2-锁定
     */
    @TableField("status")
    private Integer status;

    /**
     * 描述
     */
    @TableField("userDesc")
    private String userDesc;

    /**
     * 分组id
     */
    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 分组id
     */
    @TableField(value = "updateTime",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 分组id
     */
    @TableField("updateUser")
    private String updateUser;

    /**
     * 分组id
     */
    @TableField("createUser")
    private String createUser;

    /**
     * 生日
     */
    @TableField("birthday")
    private String birthday;

    /**
     * 企业名称
     */
    @TableField("company")
    private String company;

    /**
     * 用户所属部门id
     */
    @TableField("deptId")
    private String deptId;

    /**
     * 是否在职：0待业1在职
     */
    @TableField("incumbency")
    private Integer incumbency;

    /**
     * qq号
     */
    @TableField("qq")
    private String qq;

    /**
     * 分组id
     */
    @TableField("rongToken")
    private String rongToken;

    /**
     * 身份证号
     */
    @TableField("idCard")
    private String idCard;

    /**
     * 身份证正面照
     */
    @TableField("cardNoImg")
    private String cardNoImg;

    /**
     * 身份证反面照
     */
    @TableField("cardnoImg2")
    private String cardnoImg2;

    /**
     * 国开大学学籍号
     */
    @TableField("schoolNum")
    private String schoolNum;

    /**
     * 民族
     */
    @TableField("nation")
    private String nation;

    /**
     * 国籍
     */
    @TableField("nationality")
    private String nationality;

    /**
     * 学习中心
     */
    @TableField("learnCenter")
    private String learnCenter;

    /**
     * 人脸识别0未识别 1识别用过 2 识别不通过
     */
    @TableField("licenseStatus")
    private String licenseStatus;

    /**
     * 国开学籍注册状态（0未申请 1申请 2已完善 3申请拒绝）
     */
    @TableField("schoolRegister")
    private String schoolRegister;

    /**
     * 证书总学分
     */
    @TableField("certificateScore")
    private Integer certificateScore;

    /**
     * 国开学籍申请不通过原因
     */
    @TableField("reason")
    private String reason;

    /**
     * 注册邀请人id
     */
    @TableField("reginvitedId")
    private Long reginvitedId;

    /**
     * 考试推荐人id
     */
    @TableField("examinvitedId")
    private Long examinvitedId;

    /**
     * vip购买推荐人id
     */
    @TableField("vipinvitedId")
    private Long vipinvitedId;

    /**
     * 课程购买推荐人id
     */
    @TableField("courseinvitedId")
    private Long courseinvitedId;

    /**
     * 入学时间
     */
    @TableField("schoolTime")
    private String schoolTime;

    /**
     * 自定义用户信息（json）
     */
    @TableField("custom_information")
    private String customInformation;


    private static final long serialVersionUID = 1L;



}