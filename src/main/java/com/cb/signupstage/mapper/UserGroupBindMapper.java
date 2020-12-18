package com.cb.signupstage.mapper;

import com.cb.signupstage.entity.UserGroupBind;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户分组关系表 Mapper 接口
 * </p>
 *
 * @author ly
 * @since 2020-11-13
 */
@Repository
public interface UserGroupBindMapper extends BaseMapper<UserGroupBind> {

@Select("select group_id from user_group_bind where user_id = #{id}  ")
    List<Long> selectGroupIds(Long id);
}
