package com.cb.signupstage.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private Integer sex;

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
    @TableField("deptid")
    private String deptid;

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
    @TableField("rongtoken")
    private String rongtoken;

    /**
     * 身份证号
     */
    @TableField("idcard")
    private String idcard;

    /**
     * 身份证正面照
     */
    @TableField("cardnoimg")
    private String cardnoimg;

    /**
     * 身份证反面照
     */
    @TableField("cardnoimg2")
    private String cardnoimg2;

    /**
     * 国开大学学籍号
     */
    @TableField("schoolnum")
    private String schoolnum;

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
    @TableField("learncenter")
    private String learncenter;

    /**
     * 人脸识别0未识别 1识别用过 2 识别不通过
     */
    @TableField("licensestatus")
    private String licensestatus;

    /**
     * 国开学籍注册状态（0未申请 1申请 2已完善 3申请拒绝）
     */
    @TableField("schoolregister")
    private String schoolregister;

    /**
     * 证书总学分
     */
    @TableField("certificatescore")
    private Integer certificatescore;

    /**
     * 国开学籍申请不通过原因
     */
    @TableField("reason")
    private String reason;

    /**
     * 注册邀请人id
     */
    @TableField("reginvitedid")
    private Long reginvitedid;

    /**
     * 考试推荐人id
     */
    @TableField("examinvitedid")
    private Long examinvitedid;

    /**
     * vip购买推荐人id
     */
    @TableField("vipinvitedid")
    private Long vipinvitedid;

    /**
     * 课程购买推荐人id
     */
    @TableField("schoolTime")
    private Long courseinvitedid;

    /**
     * 入学时间
     */
    @TableField("schoolTime")
    private String schooltime;

    /**
     * 自定义用户信息（json）
     */
    @TableField("custom_information")
    private String customInformation;


    private static final long serialVersionUID = 1L;



}