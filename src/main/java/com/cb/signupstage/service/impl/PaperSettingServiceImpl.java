package com.cb.signupstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.common.SignDec;
import com.cb.signupstage.dto.PaperSettingDetailDTO;
import com.cb.signupstage.dto.PaperSettingPageDTO;
import com.cb.signupstage.entity.PaperSetting;
import com.cb.signupstage.mapper.PaperSettingMapper;
import com.cb.signupstage.service.PaperSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cb.signupstage.vo.PaperSettingPageSearchVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 论文分组配置表 服务实现类
 * </p>
 *
 * @author ly
 * @since 2020-12-23
 */
@Transactional
@Service
public class PaperSettingServiceImpl extends ServiceImpl<PaperSettingMapper, PaperSetting> implements PaperSettingService {

    @Autowired
    private PaperSettingMapper paperSettingMapper;

    @Override
    public Page<PaperSettingPageDTO> queryPaperPage(Page page, PaperSettingPageSearchVo vo,String dataBase) {
        QueryWrapper<PaperSettingPageSearchVo> wrapper = new QueryWrapper();
        wrapper.eq("ps.deleted", SignDec.deletedType.UN_DELETED.getCode())
                .orderByDesc("ps.create_time");
        if (!ObjectUtils.isEmpty(vo.getPaperGroupName())){
            wrapper.like("ps.paper_group_name",vo.getPaperGroupName());
        }

            if(!ObjectUtils.isEmpty(vo.getSource())){
                wrapper.eq("ps.source",vo.getSource());
                }
        if(!ObjectUtils.isEmpty(vo.getTestName())){
            wrapper.like("sign.name",vo.getTestName()).or().like("ex.examName",vo.getTestName());
        }

        Page<PaperSettingPageDTO> pageBean = paperSettingMapper.queryPaperPage(page, wrapper,dataBase);
      return pageBean;
    }

    @Override
    public PaperSettingDetailDTO queryPaperSettingDetail(String id, String dataBase) {
        return paperSettingMapper.selectPaperSettingDetail(id,dataBase);
    }
}
