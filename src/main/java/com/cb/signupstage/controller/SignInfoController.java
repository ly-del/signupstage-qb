package com.cb.signupstage.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.common.SignDec;
import com.cb.signupstage.common.StatusCode;
import com.cb.signupstage.dto.PagedResult;
import com.cb.signupstage.dto.SignInfoListDTO;
import com.cb.signupstage.dto.SignInfoPageDTO;
import com.cb.signupstage.entity.SignInfo;
import com.cb.signupstage.entity.UserGroup;
import com.cb.signupstage.entity.UserGroupBind;
import com.cb.signupstage.entity.UserInfo;
import com.cb.signupstage.service.SignInfoService;
import com.cb.signupstage.service.UserGroupBindService;
import com.cb.signupstage.utils.QrCodeUtil;
import com.cb.signupstage.vo.SignInfoVo;
import com.cb.signupstage.vo.UserSignSearchVo;
import com.github.pagehelper.Page;
import io.swagger.annotations.*;
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

    @Autowired
    private UserGroupBindService userGroupBindService;

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
    public ResultBean<SignInfoPageDTO> queryPage(
            @ApiParam(name = "pageNum", value = "当前页数" ,example = "1")
            @RequestParam(  required = true ,defaultValue = "1") Integer pageNum,
            @ApiParam(name = "pageSize", value = "每页查询的条数" ,example = "10")
            @RequestParam( required = true ,defaultValue = "10") Integer pageSize, @RequestHeader Long accountId) {
        Page<SignInfo> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        SignInfoPageDTO pagedResult = signInfoService.queryPage(page, accountId);
        return new ResultBean<>(StatusCode.SUCCESS_CODE,null,true,pagedResult);
    }

    @ApiOperation("报名信息发布")
    @PostMapping(value = "/release/signinfo")
    public ResultBean releaseSignInfo(@RequestBody SignInfo signInfo, @RequestHeader Long accountId) {
       if (ObjectUtils.isEmpty(signInfo.getGroupId())){
           return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).result(null).failMsg("报名发布必须选择分组").build();
       }
        signInfo.setRelease(SignDec.RELEASE_IS_RELEASE);
        QueryWrapper<SignInfo> wrapper = new QueryWrapper<>(signInfo);
      //  signInfoService.update(wrapper);
        //生成
        String aaa = QrCodeUtil.createQrCode("http://www.baidu.com", "D:/software/testCode", "aaa");
        return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).data(aaa).result(null).failMsg(null).build();
    }


    @ApiOperation("查看报名人数列表")
    @PostMapping(value = "/page/usersignPage")
    public ResultBean<UserSignSearchVo> usersignPage(@RequestBody UserSignSearchVo vo, @RequestHeader Long accountId) {
        if (ObjectUtils.isEmpty(vo.getSignId())){
            return new ResultBean<>(500,"报名id不能为空",false,null);
        }
        Page<SignInfo> page = new Page<>();
        page.setPageNum(vo.getPageNum());
        page.setPageSize(vo.getPageSize());
        List<UserSignSearchVo> pagedResult = signInfoService.queryUserSignPage(page,vo, accountId);

          return new ResultBean(StatusCode.SUCCESS_CODE,null,true,pagedResult);
    }

    @ApiOperation("移动考生到组")
    @PostMapping(value = "/move/userToGroup")
    public ResultBean moveUserGroup(
            @ApiParam(name = "ids", value = "用户id集合" )
            @RequestParam List<Long> ids,
            @ApiParam(name = "groupId", value = "组id" ,example = "1")
            @RequestParam Long groupId,
            @RequestHeader Long accountId) {
        if (ObjectUtils.isEmpty(groupId)){
            return new ResultBean<>(500,"分组id不能为空",false,null);
        }
        LambdaUpdateWrapper<UserGroupBind> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.in(UserGroupBind::getUserId, ids).set(UserGroupBind::getGroupId, groupId);

        boolean update = userGroupBindService.update(null, lambdaUpdateWrapper);

        return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).result(update).failMsg(null).build();
    }









}
