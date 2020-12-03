package com.cb.signupstage.vo;

import com.cb.signupstage.entity.SignInfo;
import com.cb.signupstage.entity.SignInfoForm;
import lombok.Data;

import java.util.List;

/**
 * @author: ly
 * @time: 2020/11/27 16:07
 * @description:
 */
@Data
public class SignInfoUpdateVo extends SignInfo {
    private List<SignInfoForm> formList;
}
