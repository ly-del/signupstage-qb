package com.cb.signupstage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cb.signupstage.common.FailStatusMsg;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.common.SignDec;
import com.cb.signupstage.common.StatusCode;
import com.cb.signupstage.entity.SignInfoForm;
import com.cb.signupstage.service.SignInfoFormService;
import com.cb.signupstage.service.SignInfoService;
import com.cb.signupstage.vo.SignInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 报名页面设置表 前端控制器
 * </p>
 *
 * @author ly
 * @since 2020-11-16
 */
@Api(tags = "报名页面设置")
@RestController
@RequestMapping("/sign-info-form")
public class SignInfoFormController {

    @Autowired
    private SignInfoFormService signInfoFormService;


    @ApiOperation("用户报名 页面设置 查询 ")
    @PostMapping(value = "/query/infoSetting")
    public ResultBean queryInfoSetting(
            @ApiParam(name = "signInfoId", value = "报名信息表id")
            @RequestBody Long signInfoId,
            @RequestHeader Long accountId) {
        if(  ObjectUtils.isEmpty( signInfoId)){
            return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).result(false).build();
        }

        SignInfoForm signInfoForm = new SignInfoForm();
        signInfoForm.setStatus(SignDec.STATUS_UN_DELETED);
        signInfoForm.setSignInfoId(signInfoId);
        QueryWrapper<SignInfoForm> wrapper = new QueryWrapper<>(signInfoForm);

        SignInfoForm one = signInfoFormService.getOne(wrapper);
        if (ObjectUtils.isEmpty(one)){
            return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg(FailStatusMsg.QUERY_NOT_EXIST_DATA).build();
        }
        return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).result(true).data(one).failMsg(null).build();
    }


    @ApiOperation("用户报名 页面设置 修改 ")
    @PostMapping(value = "/update/infoSetting")
    public ResultBean updateInfoSetting(
            @RequestBody SignInfoForm signInfoForm,
            @RequestHeader Long accountId) {
        if(  ObjectUtils.isEmpty( signInfoForm.getSignInfoId())){
            return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).result(false).build();
        }
        signInfoForm.setUpdateTime(LocalDateTime.now());
        signInfoForm.setUpdateUser(accountId);
        boolean update = signInfoFormService.saveOrUpdate(signInfoForm);
        return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).result(update).data(null).failMsg(null).build();
    }

}
