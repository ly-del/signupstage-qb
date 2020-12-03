package com.cb.signupstage.mapper;

import com.cb.signupstage.dto.SignInfoFormDTO;
import com.cb.signupstage.entity.SignInfoForm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 报名页面设置表 Mapper 接口
 * </p>
 *
 * @author ly
 * @since 2020-11-16
 */
@Repository
public interface SignInfoFormMapper extends BaseMapper<SignInfoForm> {

    List<SignInfoFormDTO> getFormList(Long signInfoId);
}
