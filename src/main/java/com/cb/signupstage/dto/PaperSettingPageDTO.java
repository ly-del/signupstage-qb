package com.cb.signupstage.dto;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.cb.signupstage.common.SignDec;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;

/**
 * @author: ly
 * @time: 2020/12/24 15:38
 * @description:
 */
@Data
public class PaperSettingPageDTO {
    private Long id;
    /**
     * 报名id
     */
    private Long testId;
    /**
     * 报名名称
     */
    private String testName;
    /**
     * 报名来源
     */
    @JsonProperty("source")
    private SignDec.ProfessionalOrSimple source;
    /**
     * 分组名称
     */
    private String paperGroupName;
    private String paperGroupDec;

    private String auditTeacher;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;


}
