package com.cb.signupstage.service.impl;

import com.cb.signupstage.dto.SignInfoFormDTO;
import com.cb.signupstage.entity.SignInfoForm;
import com.cb.signupstage.mapper.SignInfoFormMapper;
import com.cb.signupstage.service.SignInfoFormService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cb.signupstage.vo.SignInfoUpdateVo;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报名页面设置表 服务实现类
 * </p>
 *
 * @author ly
 * @since 2020-11-16
 */
@Service
@Transactional
public class SignInfoFormServiceImpl extends ServiceImpl<SignInfoFormMapper, SignInfoForm> implements SignInfoFormService {

    @Autowired
   private SignInfoFormMapper signInfoFormMapper;

    @Override
    public List<SignInfoFormDTO> getFormList(Long signInfoId) {
        return signInfoFormMapper.getFormList(signInfoId);
    }

    @Override
    public boolean batch(SignInfoUpdateVo vo ,Long accountId) {
        Map<String,Object> map = new HashedMap<>();

        map.put("sign_info_id",vo.getId());
        //先删除 在新增
        int i = signInfoFormMapper.deleteByMap(map);
        if (i<1){
         return  false;
        }
        //新增
        List<SignInfoForm> formList = vo.getFormList();
        for (SignInfoForm signInfoForm : formList) {
            signInfoForm.setSignInfoId(vo.getId());
            signInfoForm.setAccountId(accountId);
        }
        boolean b = saveBatch(vo.getFormList());
        return b;
    }
}
