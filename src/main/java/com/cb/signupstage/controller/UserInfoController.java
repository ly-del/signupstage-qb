package com.cb.signupstage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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
import com.cb.signupstage.vo.UserSelectPageVo;
import com.cb.signupstage.vo.UserSignBindVo;
import com.github.pagehelper.Page;
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
@RequestMapping("api/sign-up")
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
    public ResultBean saveUserInfo(@RequestBody UserInfoPageDTO userInfoDTO, @RequestHeader Long accounId) {
        return userInfoService.saveUserInfo(userInfoDTO, accounId);

    }

    /**
     * 用户删除
     *
     * @param
     * @return
     */
    @ApiOperation("用户删除")
    @PostMapping(value = "/delete/userinfo")
    public ResultBean deleteUserInfo(@RequestBody Map<String ,Object> map ) {
        List<Long> ids = (List<Long>) map.get("id");
        LambdaUpdateWrapper<UserInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.in(UserInfo::getId, ids).set(UserInfo::getStatus, SignDec.STATUS_DELETED);
        userInfoService.update(null,lambdaUpdateWrapper);
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
    public ResultBean payPage(@RequestBody UserSelectPageVo vo, @RequestHeader Long accounId) {

        Page<UserInfo> page = new Page<>();
        page.setPageNum(vo.getJumpPage());
        page.setPageSize(vo.getPageSize());

        PagedResult<UserInfoPageDTO> mapPagedResult = userInfoService.pageQuery(page, vo, accounId);
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

    @ApiOperation("用户移动到组")
    @PostMapping(value = "/move/userToGroup")
    public ResultBean moveUserToGroup(@RequestBody Map<String,Object> map, @RequestHeader Long accountId) {
        List<Long> ids = (List<Long>) map.get("id");
       Long groupId = Long.valueOf((int) map.get("groupId"));

        LambdaUpdateWrapper<UserGroupBind> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.in(UserGroupBind::getUserId, ids).set(UserGroupBind::getGroupId, groupId);
        boolean b = userGroupBindService.update(null, lambdaUpdateWrapper);

        return  ResultBean.builder().data(null).result(b).failMsg(null).statusCode(StatusCode.SUCCESS_CODE).build();

    }


}
