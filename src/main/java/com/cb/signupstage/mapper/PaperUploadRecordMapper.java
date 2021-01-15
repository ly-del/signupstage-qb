package com.cb.signupstage.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.dto.*;
import com.cb.signupstage.entity.PaperUploadRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cb.signupstage.vo.PaperInterviewSettingSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 论文上传记录表 Mapper 接口
 * </p>
 *
 * @author ly
 * @since 2020-12-25
 */
@Repository
public interface PaperUploadRecordMapper extends BaseMapper<PaperUploadRecord> {

    Page<PaperUploadPageDTO> queryPaperUploadPage(Page page,@Param("userId") Long userId);

    //查询 考生信息
    PaperReviewDetailDTO selectInfoById(@Param("id") Long id,@Param("dataBase") String dataBase);

    List<PaperApplyDTO> selectProfessionalApplyList(@Param("dataBase") String dataBase);

    List<PaperApplyDTO> selectSimpleApplyList();

}
