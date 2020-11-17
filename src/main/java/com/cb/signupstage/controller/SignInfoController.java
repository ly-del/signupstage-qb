package com.cb.signupstage.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.common.SignDec;
import com.cb.signupstage.common.StatusCode;
import com.cb.signupstage.dto.PagedResult;
import com.cb.signupstage.dto.SignInfoListDTO;
import com.cb.signupstage.dto.SignInfoPageDTO;
import com.cb.signupstage.entity.SignInfo;
import com.cb.signupstage.entity.UserInfo;
import com.cb.signupstage.service.SignInfoService;
import com.cb.signupstage.vo.SignInfoVo;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报名信息表 前端控制器
 * </p>
 *
 * @author MyBatisPlusGenerater
 * @since 2020-11-13
 */
@Api(tags = "报名设置基础信息")
@RestController
@RequestMapping("/sign-info")
public class SignInfoController {



    @Autowired
    private SignInfoService signInfoService;


    @ApiOperation("新建 和 复制 一个报名设置基础信息")
    @PostMapping(value = "/save/info")
    public ResultBean saveInfo(@RequestBody SignInfoVo vo, @RequestHeader Long accountId) {


        return  signInfoService.saveOrCopy(vo,accountId);
    }

    @ApiOperation("更新 报名设置基础信息")
    @PostMapping(value = "/update/signinfo")
    public ResultBean updateInfo(@RequestBody SignInfo signInfo, @RequestHeader Long accountId) {

        signInfo.setUpdateTime(LocalDateTime.now());
        boolean b = signInfoService.saveOrUpdate(signInfo);
        return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).result(b).failMsg(null).build();
    }

    @ApiOperation("删除 报名设置基础信息")
    @PostMapping(value = "delete/signinfo")
    public ResultBean deleteInfo(@RequestBody List<Long> ids, @RequestHeader Long accountId) {

        List<SignInfo> signInfoList = new ArrayList<>();
        ids.forEach(id->{
            SignInfo signInfo = new SignInfo();
            signInfo.setId(id);
            signInfo.setStatus(SignDec.STATUS_DELETED);
            signInfo.setUpdateTime(LocalDateTime.now());
        });

        boolean b = signInfoService.updateBatchById(signInfoList);
        return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).result(b).failMsg(null).build();
    }

    @ApiOperation("根据id 查询 报名设置基础信息")
    @PostMapping(value = "/get/oneinfo")
    public ResultBean getOneInfo(@RequestBody String id, @RequestHeader Long accountId) {
        SignInfo signInfo = signInfoService.getById(id);
        return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).result(null).data(signInfo).failMsg(null).build();
    }

    @ApiOperation("分页查询 报名管理信息")
    @PostMapping(value = "/query/pageList")
    public ResultBean queryPage(  @RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestHeader Long accountId) {
        Page<SignInfo> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        SignInfoPageDTO pagedResult = signInfoService.queryPage(page, accountId);

        return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).result(null).data(pagedResult).failMsg(null).build();
    }

    @ApiOperation("报名信息发布")
    @PostMapping(value = "/release/signinfo")
    public ResultBean releaseSignInfo(@RequestBody SignInfo signInfo, @RequestHeader Long accountId) {
       if (ObjectUtils.isEmpty(signInfo.getGroupId())){
           return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).result(null).failMsg("报名发布必须选择分组").build();
       }
        signInfo.setRelease(SignDec.RELEASE_IS_RELEASE);
        QueryWrapper<SignInfo> wrapper = new QueryWrapper<>(signInfo);
        signInfoService.update(wrapper);
        return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).result(null).failMsg(null).build();
    }










}
