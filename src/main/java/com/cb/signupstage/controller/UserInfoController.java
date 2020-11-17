package com.cb.signupstage.controller;

import com.cb.signupstage.dto.PagedResult;
import com.cb.signupstage.dto.UserGroupDTO;
import com.cb.signupstage.dto.UserInfoPageDTO;
import com.cb.signupstage.common.FailStatusMsg;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.common.StatusCode;
import com.cb.signupstage.entity.UserGroup;
import com.cb.signupstage.entity.UserInfo;
import com.cb.signupstage.service.StudentGroupService;
import com.cb.signupstage.service.UserInfoService;
import com.cb.signupstage.service.impl.UserInfoServiceImpl;
import com.cb.signupstage.vo.UserSignBindVo;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/signup")
public class UserInfoController {


    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private StudentGroupService studentGroupService;


    /**
     * 新增 修改 自定义类信息
     *
     * @param map       自定义名称
     * @param accountId
     * @return
     */
    @ApiOperation("新增 修改 自定义类信息")
    @PostMapping(value = "/save/customize")
    public ResultBean saveUserCustomize(@RequestBody Map<String, String> map, @RequestHeader Long accountId) {


        String customize = map.get("name");
        String id = map.get("id");
        log.info("save studentInfo map.{} accountId.{} ", customize, accountId);
        if (ObjectUtils.isEmpty(customize)) {
            return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).result(false).build();
        }

        return userInfoService.saveCustomize(id, customize);
    }


    /**
     * 查询 自定义列表
     *
     * @param
     * @param accountId
     * @return
     */
    @ApiOperation("查询 自定义列表")
    @GetMapping(value = "/query/customize")
    public ResultBean queryUserCustomize(@RequestHeader Long accountId) {
        return ResultBean.builder().data(userInfoService.getCustomizeList(null)).statusCode(StatusCode.SUCCESS_CODE).result(true).failMsg("列表查询成功").build();
    }

    /**
     * 删除 自定义列表
     *
     * @param
     * @param accountId
     * @return
     */
    @ApiOperation("删除 自定义列表")
    @PostMapping(value = "/delete/customize")
    public ResultBean deleteUserCustomize(@RequestBody Map<String, String> map, @RequestHeader Long accountId) {
        int i = userInfoService.deleteUserCustomize(map);
        if (i == 0) {
            return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg(FailStatusMsg.SYSTEM_EXCEPTION).build();
        }
        return ResultBean.builder().result(true).data(map).statusCode(StatusCode.SUCCESS_CODE).build();
    }

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
     * @param userInfo
     * @return
     */
    @ApiOperation("用户删除")
    @PostMapping(value = "/delete/userinfo")
    public ResultBean deleteUserInfo(@RequestBody UserInfo userInfo) {
        return userInfoService.deleteUserInfo(userInfo);

    }


    /**
     * 用户列表查询
     *
     * @param
     * @param map
     * @return
     */
    @ApiOperation("用户列表查询")
    @PostMapping(value = "/page/query")
    public ResultBean payPage(@RequestBody Map<String, Object> map, @RequestHeader Long accounId) {

        Page<UserInfo> page = new Page<>();
        page.setPageNum((Integer) map.get("pageNum"));
        page.setPageSize((Integer) map.get("pageSize"));

        PagedResult<UserInfoPageDTO> mapPagedResult = userInfoService.pageQuery(page, map, accounId);
        return ResultBean.builder().result(true).data(mapPagedResult).statusCode(StatusCode.SUCCESS_CODE).build();
    }


    @ApiOperation("创建分组")
    @PostMapping(value = "/create/group")
    public ResultBean createGroup(HttpServletRequest request, @RequestHeader Long accountId, @RequestBody UserGroup userGroupEntity) {

        log.info("student create group accountId.{} userGroupEntity.{} ", accountId, userGroupEntity.toString());
        if (ObjectUtils.isEmpty(userGroupEntity.getParentId()) || ObjectUtils.isEmpty(userGroupEntity.getGroupName())) {
            return ResultBean.builder().statusCode(StatusCode.MISSING_PARAMETERS_CODE).failMsg(FailStatusMsg.MISSING_PARAMETERS).result(false).build();
        }
        String failMsg = studentGroupService.createGroup(userGroupEntity, accountId);
        boolean createResult = StringUtils.isEmpty(failMsg);
        return ResultBean.builder().result(createResult).failMsg(failMsg).statusCode(StatusCode.SUCCESS_CODE).build();
    }

    @ApiOperation("移动分组和重命名")
    @PostMapping(value = "/update/group")
    public ResultBean updateGroup(HttpServletRequest request, @RequestHeader Long accountId, @RequestBody UserGroup userGroupEntity) {

        log.info("student create group accountId.{} userGroupEntity.{}", accountId, userGroupEntity.toString());
        if (ObjectUtils.isEmpty(userGroupEntity.getId())) {
            return ResultBean.builder().statusCode(StatusCode.MISSING_PARAMETERS_CODE).failMsg(FailStatusMsg.MISSING_PARAMETERS).result(false).build();
        }
        String failMsg = studentGroupService.updateGroup(userGroupEntity, accountId);
        boolean createResult = StringUtils.isEmpty(failMsg);
        return ResultBean.builder().result(createResult).failMsg(failMsg).statusCode(StatusCode.SUCCESS_CODE).build();
    }


    @ApiOperation("删除分组")
    @PostMapping(value = "/delete/group")
    public ResultBean deleteGroup(HttpServletRequest request, @RequestHeader Long accountId, @RequestBody UserGroup userGroupEntity) {

        log.info("student create group accountId.{} userGroupEntity.{}", accountId, userGroupEntity.toString());
        if (ObjectUtils.isEmpty(userGroupEntity.getId())) {
            return ResultBean.builder().statusCode(StatusCode.MISSING_PARAMETERS_CODE).failMsg(FailStatusMsg.MISSING_PARAMETERS).result(false).build();
        }
        String failMsg = studentGroupService.deleteGroup(userGroupEntity, accountId);
        boolean createResult = StringUtils.isEmpty(failMsg);
        return ResultBean.builder().result(createResult).failMsg(failMsg).statusCode(StatusCode.SUCCESS_CODE).build();
    }

    @ApiOperation("查询分组信息列表")
    @PostMapping(value = "/query/group")
    public ResultBean queryGroup(HttpServletRequest request, @RequestHeader Long accountId) {

        log.info("student create group accountId.{} userGroupEntity.{}", accountId);

        List<UserGroupDTO> groupList = studentGroupService.queryGroup( accountId);
        boolean createResult = StringUtils.isEmpty(groupList);
        return ResultBean.builder().data(groupList).result(createResult).failMsg(null).statusCode(StatusCode.SUCCESS_CODE).build();
    }



    @ApiOperation("用户报名")
    @PostMapping(value = "/bind/info")
    public ResultBean userInfoBind(@RequestBody UserSignBindVo vo, @RequestHeader Long accountId) {
     return  userInfoService.userInfoBind(vo, accountId);

    }

}
