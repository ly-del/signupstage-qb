package com.cb.signupstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.dto.PagedResult;
import com.cb.signupstage.dto.SignInfoPageDTO;
import com.cb.signupstage.entity.SignInfo;
import com.cb.signupstage.vo.SignInfoVo;
import com.cb.signupstage.vo.UserSignSearchVo;
import com.github.pagehelper.Page;

import java.util.List;

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


    /**
     *     分页查询 报名管理信息
     * @param page
     * @param accountId
     * @return
     */
    SignInfoPageDTO queryPage(Page<SignInfo> page, Long accountId);

    /**
     *       查看报名人数列表
     * @param page
     * @param vo
     * @param accountId
     * @return
     */
    List<UserSignSearchVo> queryUserSignPage(Page<SignInfo> page, UserSignSearchVo vo, Long accountId);
}
