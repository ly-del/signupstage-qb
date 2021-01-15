package com.cb.signupstage.service;

import com.cb.signupstage.entity.PaperInterviewScore;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 论文面试评分表 服务类
 * </p>
 *
 * @author ly
 * @since 2020-12-29
 */
public interface PaperInterviewScoreService extends IService<PaperInterviewScore> {


    boolean calculateAverageScore(Long paperId);
}
