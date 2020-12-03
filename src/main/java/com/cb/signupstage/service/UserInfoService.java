package com.cb.signupstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.dto.PagedResult;
import com.cb.signupstage.dto.UserInfoPageDTO;
import com.cb.signupstage.entity.SignInfo;
import com.cb.signupstage.entity.UserCustomize;
import com.cb.signupstage.entity.UserInfo;
import com.cb.signupstage.vo.UserSelectPageVo;
import com.cb.signupstage.vo.UserSignBindVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserInfoService extends IService<UserInfo> {
    ResultBean saveCustomize(Map<String,String> map,Long accountId);

    List getCustomizeList(UserCustomize customize);

    int deleteUserCustomize(Long id);

    ResultBean saveUserInfo(UserInfoPageDTO userInfoDTO, Long accounId);

    ResultBean deleteUserInfo(UserInfo userInfo);

    PagedResult<UserInfoPageDTO> pageQuery(Page<UserInfo> page, UserSelectPageVo vo, Long accounId);

    ResultBean userInfoBind(UserSignBindVo vo, Long accountId);

    UserInfoPageDTO getUserById(@Param("id") Long id);
}
