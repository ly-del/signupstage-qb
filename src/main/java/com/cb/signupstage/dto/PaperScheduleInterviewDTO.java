package com.cb.signupstage.dto;

import com.cb.signupstage.entity.PaperInterviewSetting;
import com.cb.signupstage.entity.PaperUploadRecord;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author: ly
 * @time: 2020/12/28 13:51
 * @description:  查看预约面试 页面
 */
@Data
public class PaperScheduleInterviewDTO extends PaperUploadRecord {

    private String userName;

    private  List timeList;
}
