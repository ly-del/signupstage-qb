package com.cb.signupstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.common.SignDec;
import com.cb.signupstage.dto.*;
import com.cb.signupstage.entity.PaperUploadRecord;
import com.cb.signupstage.mapper.PaperUploadRecordMapper;
import com.cb.signupstage.service.PaperUploadRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cb.signupstage.utils.CheckPhoneUtil;
import com.cb.signupstage.utils.CopyUtils;
import com.cb.signupstage.vo.PaperInterviewSettingSearchVo;
import com.cb.signupstage.vo.PaperReviewImportVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private PaperUploadRecordService paperUploadRecordService;


    @Override
    public Page<PaperUploadPageDTO> queryPaperUploadPage(Page page, Long userId) {
        Page<PaperUploadPageDTO> pageBean = paperUploadRecordMapper.queryPaperUploadPage(page, userId);
        return pageBean;
    }

    @Override
    public List<PaperApplyDTO> getProfessionalApplyList() {
        List<PaperApplyDTO> applyDTOList = paperUploadRecordMapper.selectProfessionalApplyList(dataBase);
        return applyDTOList;
    }

    @Override
    public List<PaperApplyDTO> getSimpleApplyList() {
        List<PaperApplyDTO> applyDTOList = paperUploadRecordMapper.selectSimpleApplyList();
        return applyDTOList;
    }

    @Override
    public List<PaperReviewImportErrorDTO> batchChangeRepeatStatus(List<PaperReviewImportVo> paperReviewList) {
        //成功list
        List<PaperReviewImportVo> successList = new ArrayList<>();
        //错误list
        List<PaperReviewImportErrorDTO> errorList = new ArrayList<>();
        for (PaperReviewImportVo paperReviewImportVo : paperReviewList) {
            PaperReviewImportErrorDTO paperReviewImportErrorDTO = CopyUtils.copy(paperReviewImportVo, PaperReviewImportErrorDTO.class);
            if (ObjectUtils.isEmpty(paperReviewImportVo.getRepeatStatus()) || ObjectUtils.isEmpty(paperReviewImportVo.getPaperName())) {
                //信息不全 添加进 错误列表

                paperReviewImportErrorDTO.setDec("状态或名称不能为空");
                errorList.add(paperReviewImportErrorDTO);
                continue;
            }
            //数据库如果找不到  就是无效数据

            if (!ObjectUtils.isEmpty(paperReviewImportVo.getRepeatStatus()) && !ObjectUtils.isEmpty(paperReviewImportVo.getPaperName())) {

                QueryWrapper<PaperUploadRecord> queryPaperWrapper = new QueryWrapper<>();
                queryPaperWrapper.eq("paper_name",paperReviewImportVo.getPaperName());
                List<PaperUploadRecord> paperUploadRecords = paperUploadRecordMapper.selectList(queryPaperWrapper);
                if (paperUploadRecords.size()<1){
                    //找不到对应的 添加进 错误列表
                    paperReviewImportErrorDTO.setDec("找不到对应的论文");
                    errorList.add(paperReviewImportErrorDTO);
                    continue;
                }
                if(!"不合格".equals(paperReviewImportVo.getRepeatStatus()) && !"合格".equals(paperReviewImportVo.getRepeatStatus()) ){
                    //找不到对应的 添加进 错误列表
                    paperReviewImportErrorDTO.setDec("查重状态输入不正确");
                    errorList.add(paperReviewImportErrorDTO);
                    continue;
                }

                successList.add(paperReviewImportVo);
            }
        }

        if (successList.size() > 0) {
            List<PaperUploadRecord> repeatList = new ArrayList<>();
            for (PaperReviewImportVo paperReviewImportVo : successList) {
                UpdateWrapper<PaperUploadRecord> wrapper = new UpdateWrapper<>();
                if ("不合格".equals(paperReviewImportVo.getRepeatStatus())) {
                    wrapper.set("repeat_status", 0);
                }
                if ("合格".equals(paperReviewImportVo.getRepeatStatus())) {
                    wrapper.set("repeat_status", 1);
                }
                wrapper.eq("paper_name", paperReviewImportVo.getPaperName());
                paperUploadRecordService.update(wrapper);
            }

        }


        return errorList;
    }
}
