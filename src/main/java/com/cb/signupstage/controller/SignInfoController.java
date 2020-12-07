package com.cb.signupstage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cb.signupstage.common.FailStatusMsg;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.common.SignDec;
import com.cb.signupstage.common.StatusCode;
import com.cb.signupstage.dto.CustomizeListDTO;
import com.cb.signupstage.dto.SignInfoFormDTO;
import com.cb.signupstage.dto.SignInfoPageDTO;
import com.cb.signupstage.dto.UserGroupDTO;
import com.cb.signupstage.entity.*;
import com.cb.signupstage.service.*;
import com.cb.signupstage.utils.CopyUtils;
import com.cb.signupstage.utils.ExcelUtil;
import com.cb.signupstage.utils.QrCodeUtil;
import com.cb.signupstage.vo.*;
import com.github.pagehelper.Page;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
@Slf4j
@RequestMapping("/sign-info")
public class SignInfoController {

    @Autowired
    private SignInfoService signInfoService;

    @Autowired
    private SignInfoFormService signInfoFormService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private StudentGroupService studentGroupService;



    @ApiOperation(" 复制 一个报名")
    @PostMapping(value = "/copy/info")
    public ResultBean saveInfo(@RequestBody Map<String,String> map , @RequestHeader Long accountId) {

        Long id = Long.valueOf(map.get("id"));
        String fsg = signInfoService.saveOrCopy(id, accountId);
        boolean b = StringUtils.isEmpty(fsg);
        return  ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).result(b).failMsg(null).build();
    }

    @ApiOperation(" 新建 一个报名 ")
    @PostMapping(value = "/save/info")
    public ResultBean saveInfo(@RequestBody SignInfo signInfo, @RequestHeader Long accountId) {

        return  signInfoService.saveFirst(signInfo, accountId);
    }


    @ApiOperation("更新 报名")
    @PostMapping(value = "/update/signinfo")
    public ResultBean updateInfo(@RequestBody SignInfoUpdateVo vo, @RequestHeader Long accountId) {

      //  signInfo.setUpdateTime(LocalDateTime.now());
       //更新 报名基础信息
        SignInfo signinfo = CopyUtils.copy(vo, SignInfo.class);
        signInfoService.saveOrUpdate(signinfo);
        //编辑报名需要的表单
        boolean b = signInfoFormService.batch(vo,accountId);

        return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).result(b).failMsg(null).build();
    }

    @ApiOperation("删除 报名")
    @PostMapping(value = "delete/signinfo")
    public ResultBean deleteInfo(@RequestBody Map<String ,Object> map, @RequestHeader Long accountId) {
        List<Long> ids = (List<Long>) map.get("id");

        LambdaUpdateWrapper<SignInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.in(SignInfo::getId, ids).set(SignInfo::getStatus, SignDec.STATUS_DELETED);
        boolean b = signInfoService.update(null, lambdaUpdateWrapper);

        return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).result(b).failMsg(null).build();
    }


    @ApiOperation("分页查询 报名信息")
    @PostMapping(value = "/query/pageList")
    public ResultBean<SignInfoPageDTO> queryPage(
            @RequestBody SignInfoPageSearchVo vo, @RequestHeader Long accountId) {
        Page<SignInfo> page = new Page<>();
        page.setPageNum(vo.getJumpPage());
        page.setPageSize(vo.getPageSize());
        SignInfoPageDTO pagedResult = signInfoService.queryPage(page, accountId);
        return new ResultBean<>(StatusCode.SUCCESS_CODE,null,true,pagedResult);
    }



    @ApiOperation("查看报名人数列表")
    @PostMapping(value = "/page/usersignPage")
    public ResultBean<UserSearchVo> usersignPage(@RequestBody UserSearchVo vo, @RequestHeader Long accountId) {
        if (ObjectUtils.isEmpty(vo.getSignId())){
            return new ResultBean<>(200,"报名id不能为空",true,null);
        }
        Page<SignInfo> page = new Page<>();
        page.setPageNum(vo.getJumpPage());
        page.setPageSize(vo.getPageSize());
        List<UserSearchVo> pagedResult = signInfoService.queryUserSignPage(page,vo, accountId);

          return new ResultBean(StatusCode.SUCCESS_CODE,null,true,pagedResult);
    }



    @ApiOperation("报名设置 查询 ")
    @PostMapping(value = "/query/infoSetting")
    public ResultBean queryInfoSetting(
            @RequestBody Map<String,String> map,
            @RequestHeader Long accountId) {
        Long id =  Long.valueOf(map.get("id"));
        if(  ObjectUtils.isEmpty( id)){
            return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).result(false).build();
        }

        //查报名信息基础设置
        //基本信息
        SignInfo signInfo = signInfoService.getById(id);

        if (ObjectUtils.isEmpty(signInfo)){
            return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).failMsg(FailStatusMsg.QUERY_NOT_EXIST_DATA).result(true).build();
        }
        //表单信息
        List<SignInfoFormDTO> formList = signInfoFormService.getFormList(id);

        List<SignInfoFormDTO> sortList = formList.stream().sorted(Comparator.comparing(SignInfoFormDTO::getSort)).collect(Collectors.toList());


        SignInfoSaveVo signInfoSaveVo = CopyUtils.copy(signInfo, SignInfoSaveVo.class);
        signInfoSaveVo.setFormList(sortList);
        if (ObjectUtils.isEmpty(sortList)){
            return ResultBean.builder().result(false).statusCode(StatusCode.SUCCESS_CODE).failMsg(FailStatusMsg.QUERY_NOT_EXIST_DATA).build();
        }
        return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).result(true).data(signInfoSaveVo).failMsg(null).build();
    }


    @ApiOperation("hhhhh")
    @PostMapping(value = "/ex/userToGroup")
    public void ex(HttpServletResponse response)
           {

                 String  fileName = "报名人数报表";

               // 自定义Excel文件名
               String excelName = fileName ;
               Page<SignInfo> page = new Page<>();
               page.setPageNum(1);
               page.setPageSize(10);

               UserSignSearchVo vo = new UserSignSearchVo();
               vo.setSignId(String.valueOf(1));

               List<UserSignSearchVo> pagedResult = signInfoService.querySignPage(page,vo, Long.valueOf(1));

               // easyexcel工具类实现Excel文件导出
               ExcelUtil.writeExcel(response, pagedResult, fileName, excelName, new UserSignSearchVo());

    }



    @ApiOperation("创建分组")
    @PostMapping(value = "/create/group")
    public ResultBean createGroup(HttpServletRequest request, @RequestHeader Long accountId, @RequestBody Map<String,String> map
    ) {
        String groupName = map.get("groupName");
        Long parentId = Long.valueOf(map.get("parentId"));
        log.info("student create group accountId.{} parentId.{}  groupName.{}", accountId, groupName,parentId);
        if (org.springframework.util.ObjectUtils.isEmpty(parentId) || org.springframework.util.ObjectUtils.isEmpty(groupName)) {
            return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).failMsg(FailStatusMsg.MISSING_PARAMETERS).result(false).build();
        }
        String failMsg = studentGroupService.createGroup(groupName,parentId, accountId);
        boolean createResult = org.springframework.util.StringUtils.isEmpty(failMsg);
        return ResultBean.builder().result(createResult).failMsg(failMsg).statusCode(StatusCode.SUCCESS_CODE).build();
    }

    @ApiOperation("移动分组和重命名")
    @PostMapping(value = "/update/group")
    public ResultBean updateGroup(HttpServletRequest request, @RequestHeader Long accountId, @RequestBody UserGroup userGroupEntity) {

        log.info("student create group accountId.{} userGroupEntity.{}", accountId, userGroupEntity.toString());
        if (org.springframework.util.ObjectUtils.isEmpty(userGroupEntity.getId())) {
            return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).failMsg(FailStatusMsg.MISSING_PARAMETERS).result(false).build();
        }
        String failMsg = studentGroupService.updateGroup(userGroupEntity, accountId);
        boolean createResult = org.springframework.util.StringUtils.isEmpty(failMsg);
        return ResultBean.builder().result(createResult).failMsg(failMsg).statusCode(StatusCode.SUCCESS_CODE).build();
    }


    @ApiOperation("删除分组")
    @PostMapping(value = "/delete/group")
    public ResultBean deleteGroup(HttpServletRequest request, @RequestHeader Long accountId, @RequestBody Map<String,String> map) {

        Long id = Long.valueOf(map.get("id"));
        log.info("student create group accountId.{} userGroupEntity.{}", accountId, id);
        if (org.springframework.util.ObjectUtils.isEmpty(id)) {
            return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).failMsg(FailStatusMsg.MISSING_PARAMETERS).result(false).build();
        }
        String failMsg = studentGroupService.deleteGroup(id, accountId);
        boolean createResult = org.springframework.util.StringUtils.isEmpty(failMsg);
        return ResultBean.builder().result(createResult).failMsg(failMsg).statusCode(StatusCode.SUCCESS_CODE).build();
    }

    @ApiOperation("查询分组信息列表")
    @GetMapping(value = "/query/group")
    public ResultBean queryGroup(HttpServletRequest request, @RequestHeader Long accountId) {

        log.info("student create group accountId.{} userGroupEntity.{}", accountId);

        List<UserGroupDTO> groupList = studentGroupService.queryGroup( accountId);
        return ResultBean.builder().data(groupList).result(true).failMsg(null).statusCode(StatusCode.SUCCESS_CODE).build();
    }


    /**
     * 新增 修改 自定义类信息
     *
     * @param
     * @param accountId
     * @return
     */
    @ApiOperation("新增 修改 自定义类信息")
    @PostMapping(value = "/save/customize")
    public ResultBean saveUserCustomize(@RequestBody Map<String,String> map,
                                        @RequestHeader Long accountId) {

        String customize = map.get("customize");

        log.info("save studentInfo map.{} accountId.{} ", customize, accountId);
        if (org.springframework.util.ObjectUtils.isEmpty(customize)) {
            return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).result(false).build();
        }

        return userInfoService.saveCustomize(map,accountId);
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
    public ResultBean<List<CustomizeListDTO>> queryUserCustomize(@RequestHeader Long accountId) {
        List customizeList = userInfoService.getCustomizeList(null);
        return new ResultBean<>(StatusCode.SUCCESS_CODE,null,true,customizeList);
    }

    /**
     * 删除 自定义
     *
     * @param
     * @param ”id“ 自定义属性id
     * @return
     */
    @ApiOperation("删除 自定义")
    @PostMapping(value = "/delete/customize")
    public ResultBean deleteUserCustomize(@RequestBody Map<String,String> map, @RequestHeader Long accountId) {
        Long id = Long.valueOf(map.get("id"));
        int i = userInfoService.deleteUserCustomize(id);
        if (i < 1) {
            return ResultBean.builder().result(false).statusCode(StatusCode.SUCCESS_CODE).failMsg(FailStatusMsg.SYSTEM_EXCEPTION).build();
        }
       boolean b = false;
        //先查询 有没有 占用
        QueryWrapper select = new QueryWrapper();
        select.in("customize_id",id);
        List list = signInfoFormService.list(select);
        b=true;
        if (list.size()>0){
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.in("customize_id",id);

             b = signInfoFormService.remove(wrapper);
        }
        //TODO 删除之前已有的 报名

        return ResultBean.builder().result(b).data(null).statusCode(StatusCode.SUCCESS_CODE).build();
    }

}
