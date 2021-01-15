package com.cb.signupstage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.dto.PaperApplyDTO;
import com.cb.signupstage.dto.PaperInterviewSettingExportDTO;
import com.cb.signupstage.dto.PaperInterviewSettingPageDTO;
import com.cb.signupstage.dto.PaperUploadPageDTO;
import com.cb.signupstage.entity.PaperUploadRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cb.signupstage.vo.PaperInterviewSettingSearchVo;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * <p>
 * 论文上传记录表 服务类
 * </p>
 *
 * @author ly
 * @since 2020-12-25
 */
public interface PaperUploadRecordService extends IService<PaperUploadRecord> {



    Page<PaperUploadPageDTO> queryPaperUploadPage(Page page, Long userId);

    /**
     *  查询专业版报名list
     * @param dataBase：指定数据库
     * @return
     */
    List<PaperApplyDTO> getProfessionalApplyList();

    /**
     * 查询简易版报名list
     * @return
     */
    List<PaperApplyDTO> getSimpleApplyList();
}
