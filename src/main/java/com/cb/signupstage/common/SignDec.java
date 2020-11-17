package com.cb.signupstage.common;

import java.math.BigDecimal;

/**
 * @author: ly
 * @time: 2020/11/13 9:25
 * @description:
 */
public class SignDec {

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

    public final static Integer AUTO_JUMP = 2;

    //报名发布状态  1已发布 2未发布
    public final static Integer RELEASE_IS_RELEASE = 1;

    public final static Integer RELEASE_NOT_RELEASE = 2;

}
