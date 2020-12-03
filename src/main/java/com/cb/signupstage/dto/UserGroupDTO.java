package com.cb.signupstage.dto;

import com.cb.signupstage.entity.UserGroup;
import com.cb.signupstage.vo.UserGroupTitleVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author: ly
 * @time: 2020/11/14 15:27
 * @description:
 */
@Getter
@Setter
@ToString
public class UserGroupDTO  extends UserGroupTitleVo {



    private List<UserGroupTitleVo> children;

}
