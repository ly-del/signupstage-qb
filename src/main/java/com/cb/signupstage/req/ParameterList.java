package com.cb.signupstage.req;

import com.cb.signupstage.entity.SignInfoForm;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @author: ly
 * @time: 2020/11/25 15:02
 * @description:    报名设置页面 传参对象
 */
@Data
public class ParameterList {

    //报名id
    private long signInfoId;
    //报名设置字段list
    private List<SignInfoForm> data;
}
