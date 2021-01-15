package com.cb.signupstage.vo;

import lombok.Data;

/**
 * @author: ly
 * @time: 2020/12/29 9:28
 * @description:
 */
@Data
public class PaperInterviewSettingSearchVo {

    private Integer jumpPage;

    private Integer pageSize;
    /**
     * 论文名称
     */
    private  String paperName;
    /**
     * 考试名称
     */
    private  String testName;
    /**
     * 面试结果
     */
    private  String interviewResult;
    /**
     * 考生姓名
     */
    private  String userName;
}
