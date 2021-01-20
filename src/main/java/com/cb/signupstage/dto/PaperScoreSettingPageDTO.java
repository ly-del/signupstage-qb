package com.cb.signupstage.dto;

import com.cb.signupstage.common.SignDec;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: ly
 * @time: 2021/1/19 15:51
 * @description:  及格分数 分页列表
 */
@Data
public class PaperScoreSettingPageDTO {
    private Integer jumpPage;
    private Integer pageSize;
    private String testName;
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;
    private SignDec.ProfessionalOrSimple source;
    private Integer score;

}
