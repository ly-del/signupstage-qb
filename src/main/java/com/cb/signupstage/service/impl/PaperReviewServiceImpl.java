package com.cb.signupstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.dto.PaperReviewDetailDTO;
import com.cb.signupstage.dto.PaperReviewExportDTO;
import com.cb.signupstage.dto.PaperReviewPageDTO;
import com.cb.signupstage.entity.PaperReview;
import com.cb.signupstage.entity.PaperUploadRecord;
import com.cb.signupstage.mapper.PaperReviewMapper;
import com.cb.signupstage.mapper.PaperUploadRecordMapper;
import com.cb.signupstage.service.PaperReviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cb.signupstage.vo.PaperSettingPageSearchVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 论文审核记录表 服务实现类
 * </p>
 *
 * @author ly
 * @since 2020-12-25
 */
@Transactional
@Service
public class PaperReviewServiceImpl extends ServiceImpl<PaperReviewMapper, PaperReview> implements PaperReviewService {

    @Autowired
    private PaperReviewMapper paperReviewMapper;

    @Autowired
    private PaperUploadRecordMapper paperUploadRecordMapper;

    @Override
    public Page<PaperReviewPageDTO> queryPaperReviewPage(Page page, PaperSettingPageSearchVo vo,String dataBase) {

        Page<PaperReviewPageDTO> pageBean = paperReviewMapper.queryPaperReviewPage(page, vo,dataBase);

        return pageBean;

    }

    @Override
    public PaperReviewDetailDTO getPaperReviewDetail(Long id,String dataBase) {
        PaperReviewDetailDTO paperReviewDetailDTO = paperUploadRecordMapper.selectInfoById(id,dataBase);

        if (!ObjectUtils.isEmpty(paperReviewDetailDTO)){

            //查询审核记录
            QueryWrapper<PaperReview> wrapper = new QueryWrapper();
            wrapper.eq("user_id",paperReviewDetailDTO.getUserId());
            List<PaperReview> paperReviews = paperReviewMapper.selectList(wrapper);
            paperReviewDetailDTO.setReviewList(paperReviews);
        }
        return paperReviewDetailDTO;
    }

    @Override
    public List<PaperReviewExportDTO> exportPageReviewRecordList(String ids) {
        List<PaperReviewExportDTO> dto = paperReviewMapper.getPaperReviewList(ids);
        return dto;
    }
}
