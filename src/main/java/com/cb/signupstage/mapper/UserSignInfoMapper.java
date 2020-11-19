package com.cb.signupstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cb.signupstage.entity.UserSignInfo;
import com.cb.signupstage.vo.UserSignBindVo;
import com.cb.signupstage.vo.UserSignSearchVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSignInfoMapper extends BaseMapper<UserSignInfo> {


    List<UserSignInfo> getExist(@Param("entity") UserSignBindVo vo);

    //查询  报名人数 列表
    List<UserSignSearchVo> selectPageList(@Param("entity") UserSignSearchVo vo, Long accountId);
}