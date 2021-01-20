package com.cb.signupstage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.dto.PaperScoreSettingPageDTO;
import com.cb.signupstage.entity.PaperScoreSetting;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 面试及格成绩设置表 服务类
 * </p>
 *
 * @author ly
 * @since 2020-12-29
 */
public interface PaperScoreSettingService extends IService<PaperScoreSetting> {

    boolean checkIsExist(PaperScoreSetting paperScoreSetting);

    Page<PaperScoreSettingPageDTO> queryPassScorePage(Page page,PaperScoreSettingPageDTO paperScoreSettingPageDTO,String DataBase);
}
