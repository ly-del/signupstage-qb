package com.cb.signupstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.entity.UserCustomize;

import java.util.Map;


public interface UserCustomizeService extends IService<UserCustomize> {
    ResultBean saveCustomize(Map<String, String> map, Long accountId);

    int deleteUserCustomize(Long id);
}
