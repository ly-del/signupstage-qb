package com.cb.signupstage.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserInfo implements Serializable {

    private Long id;

    /**
     * 分组id
     */
    private Long userGroupId;

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
     * 城市
     */
    private String citystring;

    /**
     * '用户类型:platform-平台 isp-服务提供商 dev-自研开发者'
     */
    private String usertype;

    /**
     * 入职时间
     */
    private String hiredate;

    /**
     * 职位
     */
    private String positionname;

    /**
     * 所属部门
     */
    private String deptname;
    /**
     * 企业ID
     */
    private Long companyid;

    /**
     * 注册IP
     */
    private String registerip;

    /**
     * 用户状态0-禁用 1-启用 2-锁定
     */
    private Integer status;

    /**
     * 描述
     */
    private String userdesc;

    /**
     * 分组id
     */
    private Date createtime;

    /**
     * 分组id
     */
    private Date updatetime;

    /**
     * 分组id
     */
    private String updateuser;

    /**
     * 分组id
     */
    private String createuser;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 企业名称
     */
    private String company;

    /**
     * 用户所属部门id
     */
    private String deptid;

    /**
     * 是否在职：0待业1在职
     */
    private Integer incumbency;

    /**
     * qq号
     */
    private String qq;

    /**
     * 分组id
     */
    private String rongtoken;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 身份证正面照
     */
    private String cardnoimg;

    /**
     * 身份证反面照
     */
    private String cardnoimg2;

    /**
     * 国开大学学籍号
     */
    private String schoolnum;

    /**
     * 民族
     */
    private String nation;

    /**
     * 国籍
     */
    private String nationality;

    /**
     * 学习中心
     */
    private String learncenter;

    /**
     * 人脸识别0未识别 1识别用过 2 识别不通过
     */
    private String licensestatus;

    /**
     * 国开学籍注册状态（0未申请 1申请 2已完善 3申请拒绝）
     */
    private String schoolregister;

    /**
     * 证书总学分
     */
    private Integer certificatescore;

    /**
     * 国开学籍申请不通过原因
     */
    private String reason;

    /**
     * 注册邀请人id
     */
    private Long reginvitedid;

    /**
     * 考试推荐人id
     */
    private Long examinvitedid;

    /**
     * vip购买推荐人id
     */
    private Long vipinvitedid;

    /**
     * 课程购买推荐人id
     */
    private Long courseinvitedid;

    /**
     * 入学时间
     */
    private String schooltime;

    /**
     * 自定义用户信息（json）
     */
    private String customInformation;


    private static final long serialVersionUID = 1L;


    public UserInfo(Long id, Long userGroupId, String username, String jobno, String nickname, String avatar, String email, String mobile, Integer age, Integer sex, String address, String citystring, String usertype, String hiredate, String positionname, String deptname, Long companyid, String registerip, Integer status, String userdesc, Date createtime, Date updatetime, String updateuser, String createuser, String birthday, String company, String deptid, Integer incumbency, String qq, String rongtoken, String idcard, String cardnoimg, String cardnoimg2, String schoolnum, String nation, String nationality, String learncenter, String licensestatus, String schoolregister, Integer certificatescore, String reason, Long reginvitedid, Long examinvitedid, Long vipinvitedid, Long courseinvitedid, String schooltime, String customInformation) {
        this.id = id;
        this.userGroupId = userGroupId;
        this.username = username;
        this.jobno = jobno;
        this.nickname = nickname;
        this.avatar = avatar;
        this.email = email;
        this.mobile = mobile;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.citystring = citystring;
        this.usertype = usertype;
        this.hiredate = hiredate;
        this.positionname = positionname;
        this.deptname = deptname;
        this.companyid = companyid;
        this.registerip = registerip;
        this.status = status;
        this.userdesc = userdesc;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.updateuser = updateuser;
        this.createuser = createuser;
        this.birthday = birthday;
        this.company = company;
        this.deptid = deptid;
        this.incumbency = incumbency;
        this.qq = qq;
        this.rongtoken = rongtoken;
        this.idcard = idcard;
        this.cardnoimg = cardnoimg;
        this.cardnoimg2 = cardnoimg2;
        this.schoolnum = schoolnum;
        this.nation = nation;
        this.nationality = nationality;
        this.learncenter = learncenter;
        this.licensestatus = licensestatus;
        this.schoolregister = schoolregister;
        this.certificatescore = certificatescore;
        this.reason = reason;
        this.reginvitedid = reginvitedid;
        this.examinvitedid = examinvitedid;
        this.vipinvitedid = vipinvitedid;
        this.courseinvitedid = courseinvitedid;
        this.schooltime = schooltime;
        this.customInformation = customInformation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated
     */
    public UserInfo() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.id
     *
     * @return the value of user_info.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.id
     *
     * @param id the value for user_info.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.user_group_id
     *
     * @return the value of user_info.user_group_id
     *
     * @mbg.generated
     */
    public Long getUserGroupId() {
        return userGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.user_group_id
     *
     * @param userGroupId the value for user_info.user_group_id
     *
     * @mbg.generated
     */
    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.userName
     *
     * @return the value of user_info.userName
     *
     * @mbg.generated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.userName
     *
     * @param username the value for user_info.userName
     *
     * @mbg.generated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.jobNo
     *
     * @return the value of user_info.jobNo
     *
     * @mbg.generated
     */
    public String getJobno() {
        return jobno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.jobNo
     *
     * @param jobno the value for user_info.jobNo
     *
     * @mbg.generated
     */
    public void setJobno(String jobno) {
        this.jobno = jobno == null ? null : jobno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.nickName
     *
     * @return the value of user_info.nickName
     *
     * @mbg.generated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.nickName
     *
     * @param nickname the value for user_info.nickName
     *
     * @mbg.generated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.avatar
     *
     * @return the value of user_info.avatar
     *
     * @mbg.generated
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.avatar
     *
     * @param avatar the value for user_info.avatar
     *
     * @mbg.generated
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.email
     *
     * @return the value of user_info.email
     *
     * @mbg.generated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.email
     *
     * @param email the value for user_info.email
     *
     * @mbg.generated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.mobile
     *
     * @return the value of user_info.mobile
     *
     * @mbg.generated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.mobile
     *
     * @param mobile the value for user_info.mobile
     *
     * @mbg.generated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.age
     *
     * @return the value of user_info.age
     *
     * @mbg.generated
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.age
     *
     * @param age the value for user_info.age
     *
     * @mbg.generated
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.sex
     *
     * @return the value of user_info.sex
     *
     * @mbg.generated
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.sex
     *
     * @param sex the value for user_info.sex
     *
     * @mbg.generated
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.address
     *
     * @return the value of user_info.address
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.address
     *
     * @param address the value for user_info.address
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.cityString
     *
     * @return the value of user_info.cityString
     *
     * @mbg.generated
     */
    public String getCitystring() {
        return citystring;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.cityString
     *
     * @param citystring the value for user_info.cityString
     *
     * @mbg.generated
     */
    public void setCitystring(String citystring) {
        this.citystring = citystring == null ? null : citystring.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.userType
     *
     * @return the value of user_info.userType
     *
     * @mbg.generated
     */
    public String getUsertype() {
        return usertype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.userType
     *
     * @param usertype the value for user_info.userType
     *
     * @mbg.generated
     */
    public void setUsertype(String usertype) {
        this.usertype = usertype == null ? null : usertype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.hiredate
     *
     * @return the value of user_info.hiredate
     *
     * @mbg.generated
     */
    public String getHiredate() {
        return hiredate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.hiredate
     *
     * @param hiredate the value for user_info.hiredate
     *
     * @mbg.generated
     */
    public void setHiredate(String hiredate) {
        this.hiredate = hiredate == null ? null : hiredate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.positionName
     *
     * @return the value of user_info.positionName
     *
     * @mbg.generated
     */
    public String getPositionname() {
        return positionname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.positionName
     *
     * @param positionname the value for user_info.positionName
     *
     * @mbg.generated
     */
    public void setPositionname(String positionname) {
        this.positionname = positionname == null ? null : positionname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.deptName
     *
     * @return the value of user_info.deptName
     *
     * @mbg.generated
     */
    public String getDeptname() {
        return deptname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.deptName
     *
     * @param deptname the value for user_info.deptName
     *
     * @mbg.generated
     */
    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.companyId
     *
     * @return the value of user_info.companyId
     *
     * @mbg.generated
     */
    public Long getCompanyid() {
        return companyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.companyId
     *
     * @param companyid the value for user_info.companyId
     *
     * @mbg.generated
     */
    public void setCompanyid(Long companyid) {
        this.companyid = companyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.registerIp
     *
     * @return the value of user_info.registerIp
     *
     * @mbg.generated
     */
    public String getRegisterip() {
        return registerip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.registerIp
     *
     * @param registerip the value for user_info.registerIp
     *
     * @mbg.generated
     */
    public void setRegisterip(String registerip) {
        this.registerip = registerip == null ? null : registerip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.status
     *
     * @return the value of user_info.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.status
     *
     * @param status the value for user_info.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.userDesc
     *
     * @return the value of user_info.userDesc
     *
     * @mbg.generated
     */
    public String getUserdesc() {
        return userdesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.userDesc
     *
     * @param userdesc the value for user_info.userDesc
     *
     * @mbg.generated
     */
    public void setUserdesc(String userdesc) {
        this.userdesc = userdesc == null ? null : userdesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.createTime
     *
     * @return the value of user_info.createTime
     *
     * @mbg.generated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.createTime
     *
     * @param createtime the value for user_info.createTime
     *
     * @mbg.generated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.updateTime
     *
     * @return the value of user_info.updateTime
     *
     * @mbg.generated
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.updateTime
     *
     * @param updatetime the value for user_info.updateTime
     *
     * @mbg.generated
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.updateUser
     *
     * @return the value of user_info.updateUser
     *
     * @mbg.generated
     */
    public String getUpdateuser() {
        return updateuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.updateUser
     *
     * @param updateuser the value for user_info.updateUser
     *
     * @mbg.generated
     */
    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.createUser
     *
     * @return the value of user_info.createUser
     *
     * @mbg.generated
     */
    public String getCreateuser() {
        return createuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.createUser
     *
     * @param createuser the value for user_info.createUser
     *
     * @mbg.generated
     */
    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.birthday
     *
     * @return the value of user_info.birthday
     *
     * @mbg.generated
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.birthday
     *
     * @param birthday the value for user_info.birthday
     *
     * @mbg.generated
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.company
     *
     * @return the value of user_info.company
     *
     * @mbg.generated
     */
    public String getCompany() {
        return company;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.company
     *
     * @param company the value for user_info.company
     *
     * @mbg.generated
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.deptId
     *
     * @return the value of user_info.deptId
     *
     * @mbg.generated
     */
    public String getDeptid() {
        return deptid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.deptId
     *
     * @param deptid the value for user_info.deptId
     *
     * @mbg.generated
     */
    public void setDeptid(String deptid) {
        this.deptid = deptid == null ? null : deptid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.incumbency
     *
     * @return the value of user_info.incumbency
     *
     * @mbg.generated
     */
    public Integer getIncumbency() {
        return incumbency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.incumbency
     *
     * @param incumbency the value for user_info.incumbency
     *
     * @mbg.generated
     */
    public void setIncumbency(Integer incumbency) {
        this.incumbency = incumbency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.qq
     *
     * @return the value of user_info.qq
     *
     * @mbg.generated
     */
    public String getQq() {
        return qq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.qq
     *
     * @param qq the value for user_info.qq
     *
     * @mbg.generated
     */
    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.rongToken
     *
     * @return the value of user_info.rongToken
     *
     * @mbg.generated
     */
    public String getRongtoken() {
        return rongtoken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.rongToken
     *
     * @param rongtoken the value for user_info.rongToken
     *
     * @mbg.generated
     */
    public void setRongtoken(String rongtoken) {
        this.rongtoken = rongtoken == null ? null : rongtoken.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.idCard
     *
     * @return the value of user_info.idCard
     *
     * @mbg.generated
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.idCard
     *
     * @param idcard the value for user_info.idCard
     *
     * @mbg.generated
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.cardNoImg
     *
     * @return the value of user_info.cardNoImg
     *
     * @mbg.generated
     */
    public String getCardnoimg() {
        return cardnoimg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.cardNoImg
     *
     * @param cardnoimg the value for user_info.cardNoImg
     *
     * @mbg.generated
     */
    public void setCardnoimg(String cardnoimg) {
        this.cardnoimg = cardnoimg == null ? null : cardnoimg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.cardNoImg2
     *
     * @return the value of user_info.cardNoImg2
     *
     * @mbg.generated
     */
    public String getCardnoimg2() {
        return cardnoimg2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.cardNoImg2
     *
     * @param cardnoimg2 the value for user_info.cardNoImg2
     *
     * @mbg.generated
     */
    public void setCardnoimg2(String cardnoimg2) {
        this.cardnoimg2 = cardnoimg2 == null ? null : cardnoimg2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.schoolNum
     *
     * @return the value of user_info.schoolNum
     *
     * @mbg.generated
     */
    public String getSchoolnum() {
        return schoolnum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.schoolNum
     *
     * @param schoolnum the value for user_info.schoolNum
     *
     * @mbg.generated
     */
    public void setSchoolnum(String schoolnum) {
        this.schoolnum = schoolnum == null ? null : schoolnum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.nation
     *
     * @return the value of user_info.nation
     *
     * @mbg.generated
     */
    public String getNation() {
        return nation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.nation
     *
     * @param nation the value for user_info.nation
     *
     * @mbg.generated
     */
    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.nationality
     *
     * @return the value of user_info.nationality
     *
     * @mbg.generated
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.nationality
     *
     * @param nationality the value for user_info.nationality
     *
     * @mbg.generated
     */
    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.learnCenter
     *
     * @return the value of user_info.learnCenter
     *
     * @mbg.generated
     */
    public String getLearncenter() {
        return learncenter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.learnCenter
     *
     * @param learncenter the value for user_info.learnCenter
     *
     * @mbg.generated
     */
    public void setLearncenter(String learncenter) {
        this.learncenter = learncenter == null ? null : learncenter.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.licenseStatus
     *
     * @return the value of user_info.licenseStatus
     *
     * @mbg.generated
     */
    public String getLicensestatus() {
        return licensestatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.licenseStatus
     *
     * @param licensestatus the value for user_info.licenseStatus
     *
     * @mbg.generated
     */
    public void setLicensestatus(String licensestatus) {
        this.licensestatus = licensestatus == null ? null : licensestatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.schoolRegister
     *
     * @return the value of user_info.schoolRegister
     *
     * @mbg.generated
     */
    public String getSchoolregister() {
        return schoolregister;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.schoolRegister
     *
     * @param schoolregister the value for user_info.schoolRegister
     *
     * @mbg.generated
     */
    public void setSchoolregister(String schoolregister) {
        this.schoolregister = schoolregister == null ? null : schoolregister.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.certificateScore
     *
     * @return the value of user_info.certificateScore
     *
     * @mbg.generated
     */
    public Integer getCertificatescore() {
        return certificatescore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.certificateScore
     *
     * @param certificatescore the value for user_info.certificateScore
     *
     * @mbg.generated
     */
    public void setCertificatescore(Integer certificatescore) {
        this.certificatescore = certificatescore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.reason
     *
     * @return the value of user_info.reason
     *
     * @mbg.generated
     */
    public String getReason() {
        return reason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.reason
     *
     * @param reason the value for user_info.reason
     *
     * @mbg.generated
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.reginvitedId
     *
     * @return the value of user_info.reginvitedId
     *
     * @mbg.generated
     */
    public Long getReginvitedid() {
        return reginvitedid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.reginvitedId
     *
     * @param reginvitedid the value for user_info.reginvitedId
     *
     * @mbg.generated
     */
    public void setReginvitedid(Long reginvitedid) {
        this.reginvitedid = reginvitedid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.examinvitedId
     *
     * @return the value of user_info.examinvitedId
     *
     * @mbg.generated
     */
    public Long getExaminvitedid() {
        return examinvitedid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.examinvitedId
     *
     * @param examinvitedid the value for user_info.examinvitedId
     *
     * @mbg.generated
     */
    public void setExaminvitedid(Long examinvitedid) {
        this.examinvitedid = examinvitedid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.vipinvitedId
     *
     * @return the value of user_info.vipinvitedId
     *
     * @mbg.generated
     */
    public Long getVipinvitedid() {
        return vipinvitedid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.vipinvitedId
     *
     * @param vipinvitedid the value for user_info.vipinvitedId
     *
     * @mbg.generated
     */
    public void setVipinvitedid(Long vipinvitedid) {
        this.vipinvitedid = vipinvitedid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.courseinvitedId
     *
     * @return the value of user_info.courseinvitedId
     *
     * @mbg.generated
     */
    public Long getCourseinvitedid() {
        return courseinvitedid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.courseinvitedId
     *
     * @param courseinvitedid the value for user_info.courseinvitedId
     *
     * @mbg.generated
     */
    public void setCourseinvitedid(Long courseinvitedid) {
        this.courseinvitedid = courseinvitedid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.schoolTime
     *
     * @return the value of user_info.schoolTime
     *
     * @mbg.generated
     */
    public String getSchooltime() {
        return schooltime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.schoolTime
     *
     * @param schooltime the value for user_info.schoolTime
     *
     * @mbg.generated
     */
    public void setSchooltime(String schooltime) {
        this.schooltime = schooltime == null ? null : schooltime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.custom_information
     *
     * @return the value of user_info.custom_information
     *
     * @mbg.generated
     */
    public String getCustomInformation() {
        return customInformation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.custom_information
     *
     * @param customInformation the value for user_info.custom_information
     *
     * @mbg.generated
     */
    public void setCustomInformation(String customInformation) {
        this.customInformation = customInformation == null ? null : customInformation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userGroupId=").append(userGroupId);
        sb.append(", username=").append(username);
        sb.append(", jobno=").append(jobno);
        sb.append(", nickname=").append(nickname);
        sb.append(", avatar=").append(avatar);
        sb.append(", email=").append(email);
        sb.append(", mobile=").append(mobile);
        sb.append(", age=").append(age);
        sb.append(", sex=").append(sex);
        sb.append(", address=").append(address);
        sb.append(", citystring=").append(citystring);
        sb.append(", usertype=").append(usertype);
        sb.append(", hiredate=").append(hiredate);
        sb.append(", positionname=").append(positionname);
        sb.append(", deptname=").append(deptname);
        sb.append(", companyid=").append(companyid);
        sb.append(", registerip=").append(registerip);
        sb.append(", status=").append(status);
        sb.append(", userdesc=").append(userdesc);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", updateuser=").append(updateuser);
        sb.append(", createuser=").append(createuser);
        sb.append(", birthday=").append(birthday);
        sb.append(", company=").append(company);
        sb.append(", deptid=").append(deptid);
        sb.append(", incumbency=").append(incumbency);
        sb.append(", qq=").append(qq);
        sb.append(", rongtoken=").append(rongtoken);
        sb.append(", idcard=").append(idcard);
        sb.append(", cardnoimg=").append(cardnoimg);
        sb.append(", cardnoimg2=").append(cardnoimg2);
        sb.append(", schoolnum=").append(schoolnum);
        sb.append(", nation=").append(nation);
        sb.append(", nationality=").append(nationality);
        sb.append(", learncenter=").append(learncenter);
        sb.append(", licensestatus=").append(licensestatus);
        sb.append(", schoolregister=").append(schoolregister);
        sb.append(", certificatescore=").append(certificatescore);
        sb.append(", reason=").append(reason);
        sb.append(", reginvitedid=").append(reginvitedid);
        sb.append(", examinvitedid=").append(examinvitedid);
        sb.append(", vipinvitedid=").append(vipinvitedid);
        sb.append(", courseinvitedid=").append(courseinvitedid);
        sb.append(", schooltime=").append(schooltime);
        sb.append(", customInformation=").append(customInformation);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}