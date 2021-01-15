package com.cb.signupstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.common.SignDec;
import com.cb.signupstage.dto.PaperApplyDTO;
import com.cb.signupstage.dto.PaperInterviewSettingExportDTO;
import com.cb.signupstage.dto.PaperInterviewSettingPageDTO;
import com.cb.signupstage.dto.PaperUploadPageDTO;
import com.cb.signupstage.entity.PaperUploadRecord;
import com.cb.signupstage.mapper.PaperUploadRecordMapper;
import com.cb.signupstage.service.PaperUploadRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cb.signupstage.vo.PaperInterviewSettingSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 论文上传记录表 服务实现类
 * </p>
 *
 * @author ly
 * @since 2020-12-25
 */
@Transactional
@Service
public class PaperUploadRecordServiceImpl extends ServiceImpl<PaperUploadRecordMapper, PaperUploadRecord> implements PaperUploadRecordService {


    @Value("${dataBase}")
    private String dataBase;

    @Autowired
    private PaperUploadRecordMapper paperUploadRecordMapper;




    @Override
    public Page<PaperUploadPageDTO> queryPaperUploadPage(Page page,Long userId) {
       Page<PaperUploadPageDTO> pageBean = paperUploadRecordMapper.queryPaperUploadPage(page,userId);
        return pageBean;
    }

    @Override
    public List<PaperApplyDTO> getProfessionalApplyList() {
        List<PaperApplyDTO>  applyDTOList =  paperUploadRecordMapper.selectProfessionalApplyList(dataBase);
        return applyDTOList;
    }

    @Override
    public List<PaperApplyDTO> getSimpleApplyList() {
        List<PaperApplyDTO> applyDTOList = paperUploadRecordMapper.selectSimpleApplyList();
        return applyDTOList;
    }
}
