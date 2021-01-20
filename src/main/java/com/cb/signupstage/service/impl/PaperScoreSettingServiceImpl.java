package com.cb.signupstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.dto.PaperScoreSettingPageDTO;
import com.cb.signupstage.entity.PaperScoreSetting;
import com.cb.signupstage.mapper.PaperScoreSettingMapper;
import com.cb.signupstage.service.PaperScoreSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * 面试及格成绩设置表 服务实现类
 * </p>
 *
 * @author ly
 * @since 2020-12-29
 */
@Service
public class PaperScoreSettingServiceImpl extends ServiceImpl<PaperScoreSettingMapper, PaperScoreSetting> implements PaperScoreSettingService {

    @Autowired
    private PaperScoreSettingMapper paperScoreSettingMapper;
    @Override
    public boolean checkIsExist(PaperScoreSetting paperScoreSetting) {
        QueryWrapper<PaperScoreSetting> wrapper  =new QueryWrapper<>();
        wrapper.eq("source",paperScoreSetting.getSource())
                 .eq("test_id",paperScoreSetting.getTestId());
        List<PaperScoreSetting> paperScoreSettings = paperScoreSettingMapper.selectList(wrapper);

        return paperScoreSettings.size()==0;
    }

    @Override
    public Page<PaperScoreSettingPageDTO> queryPassScorePage(Page page,PaperScoreSettingPageDTO paperScoreSettingPageDTO, String dataBase) {


        return  paperScoreSettingMapper.getPassScorePage(page,paperScoreSettingPageDTO, dataBase);
    }


}
