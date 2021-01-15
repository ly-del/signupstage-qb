package com.cb.signupstage.service;

import com.cb.signupstage.dto.PaperScheduleInterviewDTO;
import com.cb.signupstage.entity.PaperInterviewUserRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 论文面试预约详情表 服务类
 * </p>
 *
 * @author ly
 * @since 2020-12-28
 */
public interface PaperInterviewUserRelationService extends IService<PaperInterviewUserRelation> {

    PaperScheduleInterviewDTO queryScheduleInterview(Long id,String dataBase);
}
