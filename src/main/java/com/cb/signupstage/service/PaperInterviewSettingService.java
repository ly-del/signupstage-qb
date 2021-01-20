package com.cb.signupstage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.dto.PaperInterviewSettingExportDTO;
import com.cb.signupstage.dto.PaperInterviewSettingPageDTO;
import com.cb.signupstage.entity.PaperInterviewSetting;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cb.signupstage.vo.PaperInterviewSettingSearchVo;

import java.util.List;

/**
 * <p>
 * 面试预约时间设置表 服务类
 * </p>
 *
 * @author ly
 * @since 2020-12-25
 */
public interface PaperInterviewSettingService extends IService<PaperInterviewSetting> {

    Page<PaperInterviewSettingPageDTO> queryInterviewSettingPage(Page page , PaperInterviewSettingSearchVo paperInterviewSettingSearchVo,String dataBase);

    List<PaperInterviewSettingExportDTO> exportInterviewSettingList(String ids);

    PaperInterviewSettingPageDTO queryInterviewSettingDetail(Long id);

    boolean deleteInterviewSetting(PaperInterviewSetting paperInterviewSetting);

}
