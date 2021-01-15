package com.cb.signupstage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.dto.PaperSettingDetailDTO;
import com.cb.signupstage.dto.PaperSettingPageDTO;
import com.cb.signupstage.entity.PaperSetting;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cb.signupstage.vo.PaperSettingPageSearchVo;
import org.apache.poi.ss.formula.functions.T;

/**
 * <p>
 * 论文分组配置表 服务类
 * </p>
 *
 * @author ly
 * @since 2020-12-23
 */
public interface PaperSettingService extends IService<PaperSetting> {


    //分页查询论文分组配置
    Page<T> queryPaperPage(Page<PaperSettingPageSearchVo> objectPage, PaperSettingPageSearchVo vo,String dataBase);

    //论文分组详情
    PaperSettingDetailDTO queryPaperSettingDetail(String id, String dataBase);
}
