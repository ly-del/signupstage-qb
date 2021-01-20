package com.cb.signupstage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.dto.PaperInterviewSettingExportDTO;
import com.cb.signupstage.dto.PaperInterviewSettingPageDTO;
import com.cb.signupstage.entity.PaperInterviewSetting;
import com.cb.signupstage.mapper.PaperInterviewScoreMapper;
import com.cb.signupstage.mapper.PaperInterviewSettingMapper;
import com.cb.signupstage.service.PaperInterviewSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cb.signupstage.vo.PaperInterviewSettingSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 面试预约时间设置表 服务实现类
 * </p>
 *
 * @author ly
 * @since 2020-12-25
 */
@Service
public class PaperInterviewSettingServiceImpl extends ServiceImpl<PaperInterviewSettingMapper, PaperInterviewSetting> implements PaperInterviewSettingService {

    @Autowired
    private PaperInterviewSettingMapper paperInterviewSettingMapper;

    @Override
    public Page<PaperInterviewSettingPageDTO> queryInterviewSettingPage(Page page, PaperInterviewSettingSearchVo paperInterviewSettingSearchVo,String dataBase) {
        Page<PaperInterviewSettingPageDTO> dto =  paperInterviewSettingMapper.queryInterviewSettingPage(page,paperInterviewSettingSearchVo,dataBase);
        return dto;
    }

    @Override
    public List<PaperInterviewSettingExportDTO> exportInterviewSettingList(String ids) {
        List<PaperInterviewSettingExportDTO> dto =  paperInterviewSettingMapper.exportInterviewSettingList(ids);
        return dto;
    }

    @Override
    public PaperInterviewSettingPageDTO queryInterviewSettingDetail(Long id) {
        PaperInterviewSettingPageDTO dto =  paperInterviewSettingMapper.getInterviewSettingDetail(id);
        return dto;
    }

    @Override
    public boolean deleteInterviewSetting(PaperInterviewSetting paperInterviewSetting) {
        int i = paperInterviewSettingMapper.deleteById(paperInterviewSetting.getId());
        if (i<1){
            return false;
        }
        return true;
    }
}
