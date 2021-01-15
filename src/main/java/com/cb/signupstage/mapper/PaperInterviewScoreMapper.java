package com.cb.signupstage.mapper;

import com.cb.signupstage.entity.PaperInterviewScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 论文面试评分表 Mapper 接口
 * </p>
 *
 * @author ly
 * @since 2020-12-29
 */
@Repository
public interface PaperInterviewScoreMapper extends BaseMapper<PaperInterviewScore> {

    String getAverageScore(@Param("paperId") Long paperId);
}
