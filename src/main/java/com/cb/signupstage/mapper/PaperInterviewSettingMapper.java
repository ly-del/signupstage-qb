package com.cb.signupstage.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.dto.InterviewListDTO;
import com.cb.signupstage.dto.PaperInterviewSettingExportDTO;
import com.cb.signupstage.dto.PaperInterviewSettingPageDTO;
import com.cb.signupstage.entity.PaperInterviewSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cb.signupstage.vo.PaperInterviewSettingSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 面试预约时间设置表 Mapper 接口
 * </p>
 *
 * @author ly
 * @since 2020-12-25
 */
@Repository
public interface PaperInterviewSettingMapper extends BaseMapper<PaperInterviewSetting> {


    Page<PaperInterviewSettingPageDTO> queryInterviewSettingPage(Page page, @Param("entity") PaperInterviewSettingSearchVo paperInterviewSettingSearchVo);

    List<PaperInterviewSettingExportDTO> exportInterviewSettingList(@Param("ids") String ids);

    PaperInterviewSettingPageDTO getInterviewSettingDetail(@Param("id") Long id);

    List<InterviewListDTO> selectListGroupByBookedDate(QueryWrapper wrapper);
}
