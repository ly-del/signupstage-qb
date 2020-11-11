package com.cb.signupstage.mapper;

import com.cb.signupstage.entity.UserGroupEntity;
import com.cb.signupstage.entity.UserGroupEntityExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


public interface UserGroupEntityMapper {
    long countByExample(UserGroupEntityExample example);

    int deleteByExample(UserGroupEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserGroupEntity record);

    int insertSelective(UserGroupEntity record);

    List<UserGroupEntity> selectByExample(UserGroupEntityExample example);

    UserGroupEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserGroupEntity record, @Param("example") UserGroupEntityExample example);

    int updateByExample(@Param("record") UserGroupEntity record, @Param("example") UserGroupEntityExample example);

    int updateByPrimaryKeySelective(UserGroupEntity record);

    int updateByPrimaryKey(UserGroupEntity record);

    List<UserGroupEntity> queryByIds(List<Long> idList);

    List<UserGroupEntity> queryAllByAccountId(Long accountId);
}