package com.cb.signupstage.service;

import com.cb.signupstage.dto.SignInfoFormDTO;
import com.cb.signupstage.entity.SignInfoForm;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cb.signupstage.vo.SignInfoUpdateVo;

import java.util.List;

/**
 * <p>
 * 报名页面设置表 服务类
 * </p>
 *
 * @author ly
 * @since 2020-11-16
 */
public interface SignInfoFormService extends IService<SignInfoForm> {

    //查询 报名页面属性list
    List<SignInfoFormDTO> getFormList(Long signInfoId);

    boolean batch(SignInfoUpdateVo vo,Long accountId);
}
