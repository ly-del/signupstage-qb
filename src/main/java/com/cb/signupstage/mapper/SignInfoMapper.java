package com.cb.signupstage.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cb.signupstage.dto.SignInfoPageDTO;
import com.cb.signupstage.entity.SignInfo;
import com.cb.signupstage.vo.SignInfoVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SignInfoMapper extends BaseMapper<SignInfo> {

    List<SignInfoVo> selectPageList(QueryWrapper<SignInfo> wrapper);

    SignInfoPageDTO selectCountAndCost();

    //查询本周的报名人数
    int selectThisWeekCount();

    //查询本月的报名人数
    int selectThisMonthCount();

}