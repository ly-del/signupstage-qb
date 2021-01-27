package com.cb.signupstage.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: ly
 * @time: 2020/11/13 9:25
 * @description:
 */
@Data
public class SignDec {

    public enum deletedType {
        UN_DELETED(0, "未删除"), DELETED(1, "已删除");
        private Integer code;
        private String value;

        deletedType(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }

    }

    public enum paperReviewStatus {
        REVIEW_FAIL(0, "审核不通过"), REVIEW_PAST(1, "审核通过"), REVIEW_PENDING(2, "待审核");
        private Integer code;
        private String value;

        paperReviewStatus(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        public Integer getCode() {
            return code;
        }

        @JsonValue
        public String getValue() {
            return value;
        }
    }


    public enum ProfessionalOrSimple {
        Professional(0, "专业报名"), Simple(1, "简易报名");
        private Integer code;

        private String value;

        ProfessionalOrSimple(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        public Integer getCode() {
            return code;
        }
        @JsonValue
        public String getValue() {
            return value;
        }

    }

    public enum SexEnum {
        Unknown(0, "未知"), Man(1, "男"),Woman(2,"女");
        private Integer code;

        private String value;

        SexEnum(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        public Integer getCode() {
            return code;
        }
        @JsonValue
        public String getValue() {
            return value;
        }

    }


    //报名模块
    public final static String UNSTARTED_DEC = "报名还未开放，请耐心等待!";

    public final static String COMPLETE_DEC = "报名活动已结束，您来晚一步!";

    public final static String SUCCEED_DEC = "您已完成报名，请等待管理员通知进行考试";

    public final static String FAIL_DEC = "网络出现异常，请检查网络后再重新提交";

    public final static String SHARE_DEC = "快来注册吧！";

    public final static BigDecimal COST = BigDecimal.valueOf(0);


    public final static Integer MAX_TOTAL = 3000;

    public final static Integer STATUS_UN_DELETED = 1;

    public final static Integer STATUS_DELETED = 2;

    public final static Integer NO_JUMP = 1;

    public final static Integer MANUAL_JUMP = 2;

    public final static Integer AUTO_JUMP = 3;

    //报名发布状态  1已发布 0未发布
    public final static Integer RELEASE_IS_RELEASE = 1;

    public final static Integer RELEASE_NOT_RELEASE = 0;

    //删除状态  不可删除
    public final static Integer DELETED_OK = 2;

    //删除状态 可以删除
    public final static Integer DELETED_NO = 1;


    //论文分组 考试来源
    public final static String PROFESSIONAL = "Professional";

    public final static String SIMPLE = "Simple";

    public final static Integer IS_MUST_NO = 0;

}
