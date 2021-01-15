package com.cb.signupstage.vo;

import com.cb.signupstage.common.SignDec;
import com.cb.signupstage.entity.PaperSetting;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: ly
 * @time: 2020/12/23 16:15
 * @description: 论文分组配置 查询vo
 */
@Data
public class PaperSettingPageSearchVo extends PaperSetting {
    private Integer jumpPage;

    private Integer pageSize;
    /**
     * 审核状态
     */
    private Integer reviewStatus;
    /**
     * 论文名称
     */
    private String paperName;
    /**
     * 论文分组名称
     */
    private String paperGroupName;
    /**
     * 报名名称
     */
    private String testName;
    /**
     * 删除标识
     */
    private Integer deleted;
    /**
     * 报名来源
     */
    private String  source;
}
