package com.cb.signupstage.dto;

import com.cb.signupstage.common.SignDec;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: ly
 * @time: 2020/12/30 13:36
 * @description:
 */
@Data
public class PaperUploadPageDTO {
    private Long id;
    private String paperGroupName;

    private SignDec.paperReviewStatus reviewStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;
    private String reserveTime;
    private String reviewDec;
}
