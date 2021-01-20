package com.cb.signupstage.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.dto.PaperScoreSettingPageDTO;
import com.cb.signupstage.entity.PaperScoreSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 面试及格成绩设置表 Mapper 接口
 * </p>
 *
 * @author ly
 * @since 2020-12-29
 */
@Repository
public interface PaperScoreSettingMapper extends BaseMapper<PaperScoreSetting> {

    Page<PaperScoreSettingPageDTO> getPassScorePage(Page page, @Param("entity") PaperScoreSettingPageDTO paperScoreSettingPageDTO,@Param("dataBase") String dataBase);
}
