package com.cb.signupstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cb.signupstage.entity.UserGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserGroupEntityMapper  extends BaseMapper<UserGroup> {

    List selectAllChildrensId(@Param("id") Long id);
}