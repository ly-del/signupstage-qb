package com.cb.signupstage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.common.SignDec;
import com.cb.signupstage.dto.CustomizeListDTO;
import com.cb.signupstage.dto.PagedResult;
import com.cb.signupstage.dto.UserGroupDTO;
import com.cb.signupstage.dto.UserInfoPageDTO;
import com.cb.signupstage.common.FailStatusMsg;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.common.StatusCode;
import com.cb.signupstage.entity.SignInfo;
import com.cb.signupstage.entity.UserGroup;
import com.cb.signupstage.entity.UserGroupBind;
import com.cb.signupstage.entity.UserInfo;
import com.cb.signupstage.service.UserGroupBindService;
import com.cb.signupstage.service.UserInfoService;
import com.cb.signupstage.service.impl.UserInfoServiceImpl;
import com.cb.signupstage.utils.CopyUtils;
import com.cb.signupstage.vo.UserDeletedVo;
import com.cb.signupstage.vo.UserSelectPageVo;
import com.cb.signupstage.vo.UserSignBindVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: ly
 * @time: 2020/11/11 9:11
 * @description: 考生管理 控制层
 */
@Api(tags = "用户信息列表")
@RestController
@Slf4j
@RequestMapping("/sign-up")
public class UserInfoController {


    @Autowired
    private UserInfoService userInfoService;


    @Autowired
    private UserGroupBindService userGroupBindService;




    /**
     * 用户信息保存 修改
     */
    @ApiOperation("用户信息保存 修改")
    @PostMapping(value = "/save/userinfo")
    public ResultBean saveUserInfo(@RequestBody UserInfoPageDTO userInfoDTO, @RequestHeader Long accountId) {
        return userInfoService.saveUserInfo(userInfoDTO, accountId);

    }

    /**
     *
     *考生删除  删除绑定关系 不是删除用户
     * @param
     * @return
     */
    @ApiOperation("考生删除")
    @PostMapping(value = "/delete/userinfo")
    public ResultBean deleteUserInfo(@RequestBody UserDeletedVo vo ) {
        Long userId = vo.getId();
        List<Long> groupIds = vo.getGroupIds();
        LambdaUpdateWrapper<UserGroupBind> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(UserGroupBind::getUserId, userId)
                           .in(UserGroupBind::getGroupId,groupIds)
                .set(UserGroupBind::getStatus, SignDec.STATUS_DELETED);
        userGroupBindService.update(null,lambdaUpdateWrapper);
        return ResultBean.builder().result(true).data(null).statusCode(StatusCode.SUCCESS_CODE).build();

    }

    @ApiOperation("批量删除考生")
    @PostMapping(value = "/delete/moreUserInfos")
    public ResultBean deleteUserInfo(@RequestBody Map<String,List> map) {
        List<Long> ids = map.get("ids");

        LambdaUpdateWrapper<UserGroupBind> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.in(UserGroupBind::getUserId, ids).set(UserGroupBind::getStatus, SignDec.STATUS_DELETED);
        userGroupBindService.update(null,lambdaUpdateWrapper);
        return ResultBean.builder().result(true).data(null).statusCode(StatusCode.SUCCESS_CODE).build();

    }

    /**
     * 用户列表查询
     *
     * @param
     * @param
     * @return
     */
    @ApiOperation("用户列表查询")
    @PostMapping(value = "/page/query")
    public ResultBean payPage(@RequestBody UserSelectPageVo vo, @RequestHeader Long accountId) {

        Page<UserInfo> page = new Page<>(vo.getJumpPage(), vo.getPageSize());
        IPage<UserInfoPageDTO> mapPagedResult = userInfoService.pageQuery(page, vo, accountId);
        return ResultBean.builder().result(true).data(mapPagedResult).statusCode(StatusCode.SUCCESS_CODE).build();
    }



    @ApiOperation("用户报名")
    @PostMapping(value = "/bind/info")
    public ResultBean userInfoBind(@RequestBody UserSignBindVo vo, @RequestHeader Long accountId) {
        log.info("vo.{}", vo);
        return  userInfoService.userInfoBind(vo, accountId);

    }



    @ApiOperation("用户编辑查询")
    @PostMapping(value = "/userinfo/getUserById")
    public ResultBean getUserById(@RequestBody Map<String,String> map, @RequestHeader Long accountId) {
        Long id = Long.valueOf(map.get("id"));
        UserInfoPageDTO dto = userInfoService.getUserById(id);
                  //根据id查分组
        return  ResultBean.builder().data(dto).result(true).failMsg(null).statusCode(StatusCode.SUCCESS_CODE).build();

    }




}
