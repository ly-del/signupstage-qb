package com.cb.signupstage.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.dto.PaperReviewExportDTO;
import com.cb.signupstage.dto.PaperReviewPageDTO;
import com.cb.signupstage.entity.PaperReview;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cb.signupstage.vo.PaperSettingPageSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 论文审核记录表 Mapper 接口
 * </p>
 *
 * @author ly
 * @since 2020-12-25
 */
@Repository
public interface PaperReviewMapper extends BaseMapper<PaperReview> {

      Page<PaperReviewPageDTO> queryPaperReviewPage(Page page, @Param("entity") PaperSettingPageSearchVo vo,@Param("dataBase") String dataBase) ;

    List<PaperReviewExportDTO> getPaperReviewList(@Param("ids") String ids);
}
