package com.cb.signupstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cb.signupstage.dto.UserGroupDTO;
import com.cb.signupstage.entity.UserGroup;

import java.util.List;

/**
 * @author: ly
 * @time: 2020/11/12 8:45
 * @description:
 */
public interface StudentGroupService extends IService<UserGroup> {

    String createGroup(UserGroup userGroupEntity, Long accountId);

    String updateGroup(UserGroup userGroupEntity, Long accountId);

    String deleteGroup(UserGroup userGroupEntity, Long accountId);

    /**
     * 查询 考生分组列表
     * @param
     * @param accountId
     * @return
     */
    List<UserGroupDTO> queryGroup( Long accountId);
}
