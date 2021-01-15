package com.cb.signupstage.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.dto.PaperSettingDetailDTO;
import com.cb.signupstage.dto.PaperSettingPageDTO;
import com.cb.signupstage.entity.PaperSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cb.signupstage.vo.PaperSettingPageSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 论文分组配置表 Mapper 接口
 * </p>
 *
 * @author ly
 * @since 2020-12-23
 */
@Repository
public interface PaperSettingMapper extends BaseMapper<PaperSetting> {

    Page<PaperSettingPageDTO> queryPaperPage(Page page, @Param("ew") QueryWrapper<PaperSettingPageSearchVo> wrapper, @Param("dataBase") String dataBase);


    PaperSettingDetailDTO selectPaperSettingDetail(@Param("id") String id, @Param("dataBase") String dataBase);
}
