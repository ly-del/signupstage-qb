package com.cb.signupstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.dto.SignInfoPageDTO;
import com.cb.signupstage.entity.SignInfo;
import com.cb.signupstage.vo.SignInfoSaveVo;
import com.cb.signupstage.vo.UserSearchVo;
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
    String saveOrCopy(Long id, Long accountId);


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
    List<UserSearchVo> queryUserSignPage(Page<SignInfo> page, UserSearchVo vo, Long accountId);
    List<UserSignSearchVo> querySignPage(Page<SignInfo> page, UserSignSearchVo vo, Long accountId);

    ResultBean saveFirst(SignInfo signInfo, Long accountId);
}
