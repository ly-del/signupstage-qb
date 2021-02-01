package com.cb.signupstage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.dto.PaperReviewDetailDTO;
import com.cb.signupstage.dto.PaperReviewExportDTO;
import com.cb.signupstage.dto.PaperReviewPageDTO;
import com.cb.signupstage.entity.PaperReview;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cb.signupstage.vo.PaperSettingPageSearchVo;

import java.util.List;

/**
 * <p>
 * 论文审核记录表 服务类
 * </p>
 *
 * @author ly
 * @since 2020-12-25
 */
public interface PaperReviewService extends IService<PaperReview> {

    Page<PaperReviewPageDTO> queryPaperReviewPage(Page page, PaperSettingPageSearchVo vo,String dataBase);
//审核详情
    PaperReviewDetailDTO getPaperReviewDetail(Long id,String dataBase);

    List<PaperReviewExportDTO> exportPageReviewRecordList(List<Long> ids);
}
