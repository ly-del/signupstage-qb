package com.cb.signupstage.service;

import com.cb.signupstage.common.FailStatusMsg;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.common.StatusCode;
import com.cb.signupstage.entity.UserCustomize;
import com.cb.signupstage.entity.UserInfo;
import com.cb.signupstage.mapper.UserCustomizeMapper;
import com.cb.signupstage.mapper.UserInfoMapper;
import io.netty.util.internal.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: ly
 * @time: 2020/11/11 9:38
 * @description:  考生报名 业务层
 */
@Slf4j
@Service
public class UserSignUpService {

    @Autowired
    private UserCustomizeMapper userCustomizeMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;
    /**
     * 保存 修改 自定义类信息
     * @param customize
     * @return
     */
    public ResultBean saveCustomize(String id, String customize) {
        UserCustomize userCustomize = new UserCustomize();
        if (ObjectUtils.isEmpty(id)) {
            //先查询 如果已存在 则新建失败
            userCustomize.setName(customize);
            List customizeList = getCustomizeList(userCustomize);

            if (customizeList.size()>0) {
                //存在 则创建失败
                return ResultBean.builder().data(customize).statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg(FailStatusMsg.CREATE_EXIST_DATA).build();
            }
            userCustomize.setType(1);
            userCustomize.setStatus(1);
            userCustomize.setCreateTime(new Date());
            userCustomize.setUpdateTime(new Date());
            userCustomizeMapper.insert(userCustomize);
               return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).failMsg("保存成功").build();
        }

        //修改
        userCustomize.setId(Long.valueOf(id));
        userCustomize.setName(customize);
        userCustomize.setUpdateTime(new Date());
         userCustomizeMapper.updateByPrimaryKeySelective(userCustomize);
         return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).failMsg("修改成功成功").build();

    }


    /**
     * 查询 自定义信息 列表
     */
    public List getCustomizeList(UserCustomize customize) {
        List<UserCustomize> customizeList = userCustomizeMapper.getCustomizeList(customize);
        return customizeList;
    }

    /**
     * 删除 自定义信息 列表
     */
    public int deleteUserCustomize(Map<String, String> map) {
        UserCustomize userCustomize = new UserCustomize();
        userCustomize.setId(Long.valueOf(map.get("id")));
        userCustomize.setStatus(2);
        userCustomize.setUpdateTime(new Date());
        return userCustomizeMapper.updateByPrimaryKeySelective(userCustomize);
    }

    public ResultBean saveUserInfo(UserInfo userInfo) {
        //姓名和手机号必填
        if (ObjectUtils.isEmpty(userInfo.getMobile()) || ObjectUtils.isEmpty(userInfo.getUsername())) {
            return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg("手机号或者姓名为空").build();
        }

        if (ObjectUtils.isEmpty(userInfo.getId())) {

            //先查询这个手机号是否已经注册

           List<UserInfo> userList = userInfoMapper.selectBySelect(userInfo);
           if (userList.size()>0){
               return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg("该手机号已注册").build();
           }

            userInfo.setStatus(1);
            userInfo.setCreatetime(new Date());
            userInfo.setUpdatetime(new Date());
            int insert = userInfoMapper.insert(userInfo);
            if (insert == 0) {
                return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg(FailStatusMsg.SYSTEM_EXCEPTION).build();

            }
            return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).failMsg("保存成功").build();
        }

        //修改

        //判断手机号是否重复
        List<UserInfo> userList = userInfoMapper.selectBySelect(userInfo);
       if (!userInfo.getId().equals(userList.get(0).getId())){
           //手机号已被注册
           return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg("该手机号已注册").build();

       }

        userInfo.setUpdatetime(new Date());
        int i = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if (i==0){
            return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg(FailStatusMsg.SYSTEM_EXCEPTION).build();
        }
        return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).failMsg("修改成功").build();
    }

    /**
     * 用户删除
     * @param userInfo
     * @return
     */
    public ResultBean deleteUserInfo(UserInfo userInfo) {
        userInfo.setStatus(0);
        int i = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if (i==0){
            return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg(FailStatusMsg.SYSTEM_EXCEPTION).build();
        }
        return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).failMsg("删除成功").build();
    }
}
