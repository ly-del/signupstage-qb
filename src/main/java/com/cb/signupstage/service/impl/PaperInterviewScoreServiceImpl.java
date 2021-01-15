package com.cb.signupstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cb.signupstage.entity.PaperInterviewScore;
import com.cb.signupstage.entity.PaperSetting;
import com.cb.signupstage.entity.PaperUploadRecord;
import com.cb.signupstage.mapper.PaperInterviewScoreMapper;
import com.cb.signupstage.mapper.PaperSettingMapper;
import com.cb.signupstage.mapper.PaperUploadRecordMapper;
import com.cb.signupstage.service.PaperInterviewScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 论文面试评分表 服务实现类
 * </p>
 *
 * @author ly
 * @since 2020-12-29
 */
@Slf4j
@Transactional
@Service
public class PaperInterviewScoreServiceImpl extends ServiceImpl<PaperInterviewScoreMapper, PaperInterviewScore> implements PaperInterviewScoreService {


    @Autowired
    private PaperInterviewScoreMapper paperInterviewScoreMapper;

    @Autowired
    private PaperUploadRecordMapper paperUploadRecordMapper;

    @Autowired
    private PaperSettingMapper paperSettingMapper;

    @Override
    public boolean calculateAverageScore(Long paperId) {
        //判断是否是 最后一次打分
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("paper_id", paperId);
        List list = paperInterviewScoreMapper.selectList(wrapper);

        PaperUploadRecord paperUploadRecord = paperUploadRecordMapper.selectById(paperId);
        if (!ObjectUtils.isEmpty(paperUploadRecord)) {
            PaperSetting paperSetting = paperSettingMapper.selectById(paperUploadRecord.getPaperGroupId());
            if (!ObjectUtils.isEmpty(paperSetting)){
                String auditTeacher = paperSetting.getAuditTeacher();
             if (list.size() == auditTeacher.split(",").length)  {
                 log.info("paperId:",paperId,"开始计算平均分。。。");
                 //最后一次 评分 计算平均分
               String averageScore =  paperInterviewScoreMapper.getAverageScore(paperId);
               //设置平均分
                 LambdaUpdateWrapper<PaperUploadRecord> lambdaUpdateWrapper = new LambdaUpdateWrapper();
                 lambdaUpdateWrapper.eq(PaperUploadRecord::getId,paperId).set(PaperUploadRecord::getScore,averageScore);
                 int update = paperUploadRecordMapper.update(null, lambdaUpdateWrapper);
                 if (update<1){
                     return false;
                 }
             }
            }
        }
        return true;
    }
}
