package com.cb.signupstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cb.signupstage.entity.SignInfo;
import com.cb.signupstage.entity.UserCustomize;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCustomizeMapper extends BaseMapper<UserCustomize> {


    /**
     * 查询自定义列表
     */
    List<UserCustomize> getCustomizeList( UserCustomize customize);


}