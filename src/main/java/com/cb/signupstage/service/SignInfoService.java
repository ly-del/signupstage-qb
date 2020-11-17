package com.cb.signupstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.dto.PagedResult;
import com.cb.signupstage.dto.SignInfoPageDTO;
import com.cb.signupstage.entity.SignInfo;
import com.cb.signupstage.vo.SignInfoVo;
import com.github.pagehelper.Page;

/**
 * <p>
 * 报名信息表 服务类
 * </p>
 *
 * @author MyBatisPlusGenerater
 * @since 2020-11-13
 */
public interface SignInfoService extends IService<SignInfo> {

    /**
     * 新增 和 复制 一个报名
     * @param vo
     */
    ResultBean saveOrCopy(SignInfoVo vo, Long accountId);


    SignInfoPageDTO queryPage(Page<SignInfo> page, Long accountId);
}
