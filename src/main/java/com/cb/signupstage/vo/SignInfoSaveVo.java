package com.cb.signupstage.vo;

import com.cb.signupstage.dto.SignInfoFormDTO;
import com.cb.signupstage.entity.SignInfo;
import com.cb.signupstage.entity.SignInfoForm;
import lombok.Data;

import java.util.List;

/**
 * @author: ly
 * @time: 2020/11/26 17:11
 * @description:
 */
@Data
public class SignInfoSaveVo  extends  SignInfo{

    private List<SignInfoFormDTO> formList;

}
