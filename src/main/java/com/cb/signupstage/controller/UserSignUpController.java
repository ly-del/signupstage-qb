package com.cb.signupstage.controller;

import com.cb.signupstage.common.FailStatusMsg;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.common.StatusCode;
import com.cb.signupstage.entity.UserInfo;
import com.cb.signupstage.service.UserSignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: ly
 * @time: 2020/11/11 9:11
 * @description:  考生管理 控制层
 */
@RestController
@Slf4j
@RequestMapping("signup")
public class UserSignUpController {


    @Autowired
    private UserSignUpService userSignUpService;



    /**
     *  新增 修改 自定义类信息
     * @param map  自定义名称
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/save/customize", method = RequestMethod.POST)
    public ResultBean saveUserCustomize(@RequestBody Map<String ,String> map, @RequestHeader Long accountId) {


        String customize = map.get("name");
        String id = map.get("id");
        log.info("save studentInfo map.{} accountId.{} ",customize , accountId);
        if (ObjectUtils.isEmpty(customize)) {
            return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).result(false).build();
        }

       return userSignUpService.saveCustomize(id, customize);
    }


    /**
     * 查询 自定义列表
     * @param
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/query/customize", method = RequestMethod.GET)
    public ResultBean queryUserCustomize( @RequestHeader Long accountId) {
        return ResultBean.builder().data(userSignUpService.getCustomizeList(null)).statusCode(StatusCode.SUCCESS_CODE).result(true).failMsg("列表查询成功").build();
    }

    /**
     * 删除 自定义列表
     * @param
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/delete/customize", method = RequestMethod.POST)
    public ResultBean deleteUserCustomize(@RequestBody Map<String ,String> map , @RequestHeader Long accountId) {
        int i = userSignUpService.deleteUserCustomize(map);
        if (i==0){
            return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg(FailStatusMsg.SYSTEM_EXCEPTION).build();
        }
        return ResultBean.builder().result(true).data(map).statusCode(StatusCode.SUCCESS_CODE).build();
    }

    /**
     * 用户信息保存 修改
     */
    @RequestMapping(value = "/save/userinfo", method = RequestMethod.POST)
    public ResultBean saveUserInfo(@RequestBody UserInfo userInfo) {
        return   userSignUpService.saveUserInfo(userInfo);

    }

    /**
     * 用户删除
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/delete/userinfo", method = RequestMethod.POST)
    public ResultBean deleteUserInfo(@RequestBody UserInfo userInfo) {
        return   userSignUpService.deleteUserInfo(userInfo);

    }



}
