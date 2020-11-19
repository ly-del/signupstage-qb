package com.cb.signupstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cb.signupstage.common.SignDec;
import com.cb.signupstage.dto.PagedResult;
import com.cb.signupstage.dto.UserInfoPageDTO;
import com.cb.signupstage.common.FailStatusMsg;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.common.StatusCode;
import com.cb.signupstage.entity.*;

import com.cb.signupstage.mapper.*;
import com.cb.signupstage.service.SignInfoFormService;
import com.cb.signupstage.service.UserInfoService;
import com.cb.signupstage.utils.CopyUtils;
import com.cb.signupstage.vo.UserSignBindVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
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
@Transactional
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Autowired
    private UserCustomizeMapper userCustomizeMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserGroupBindMapper userGroupBindMapper;

    @Autowired
    private UserSignInfoMapper userSignInfoMapper;

    @Autowired
    private SignInfoMapper signInfoMapper;

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

    public ResultBean saveUserInfo(UserInfoPageDTO userInfoDTO ,  Long accounId) {
        //姓名和手机号必填
        if (ObjectUtils.isEmpty(userInfoDTO.getMobile()) || ObjectUtils.isEmpty(userInfoDTO.getUsername()) ||
                ObjectUtils.isEmpty(userInfoDTO.getGroupId())
        ) {
            return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg("手机号、姓名、分组不能为空").build();
        }

        if (ObjectUtils.isEmpty(userInfoDTO.getId())) {

            //先查询这个手机号是否已经注册

            UserInfo select = new UserInfo();
            select.setMobile(userInfoDTO.getMobile());
            List<UserInfo> userList = userInfoMapper.selectBySelect(select);
           if (userList.size()>0){
               return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg("该手机号已注册").build();
           }
            UserInfo userInfo = new UserInfo();

            UserInfo copy = CopyUtils.copy(userInfoDTO, UserInfo.class);
            copy.setStatus(1);
            copy.setCreatetime(new Date());
            copy.setUpdatetime(new Date());
            userInfoMapper.insert(copy);

            //用户分组关系
            UserGroupBind userGroupBind = new UserGroupBind();
            userGroupBind.setUserId(copy.getId());
            userGroupBind.setAccountId(accounId);
            userGroupBind.setCreateTime(LocalDateTime.now());
            userGroupBind.setGroupId(userInfoDTO.getGroupId());
            userGroupBind.setStatus(1);
            userGroupBind.setUpdateTime(LocalDateTime.now());
            //添加用户分组关系表数据
            userGroupBindMapper.insert(userGroupBind);

            return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).failMsg("保存成功").build();
        }

        //修改

        //判断手机号是否重复
        UserInfo select = new UserInfo();
        select.setMobile(userInfoDTO.getMobile());
        List<UserInfo> userList = userInfoMapper.selectBySelect(select);
       if (!userInfoDTO.getId().equals(userList.get(0).getId())){
           //手机号已被注册
           return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg("该手机号已注册").build();

       }

        UserInfo copy = CopyUtils.copy(userInfoDTO, UserInfo.class);
        copy.setUpdatetime(new Date());
        int i = userInfoMapper.updateByPrimaryKeySelective(copy);

        if (i==0){
            return ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg(FailStatusMsg.SYSTEM_EXCEPTION).build();
        }

        UserGroupBind userGroupBind = new UserGroupBind();
        userGroupBind.setUserId(copy.getId());
        userGroupBind.setGroupId(userInfoDTO.getGroupId());
        userGroupBindMapper.updateById(userGroupBind);
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




    public PagedResult<UserInfoPageDTO> pageQuery(Page<UserInfo> page, Map<String, Object> map,Long accounId) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        //查询列表
        List<UserInfoPageDTO> userInfos = userInfoMapper.pageQuery(map, accounId);

        return  new PagedResult<>(userInfos);
    }

    public ResultBean userInfoBind(UserSignBindVo vo, Long accountId) {

        //报名之前 先查询 是否已经报过名 (目前是手机号做唯一识别)  存在
        List<UserSignInfo> bindList =userSignInfoMapper.getExist(vo);
        if (bindList.size()>0){
            //已经存在当前报名的 报名信息记录 不能重复报名
            return  ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg(FailStatusMsg.CREATE_EXIST_DATA).build();
        }
           //创建报名信息
        //先创建用户信息
        // 存在就不创建
        UserInfo userInfo = new UserInfo();
        userInfo.setMobile(vo.getMobile());
        userInfo.setUsername(vo.getUsername());
        userInfo.setStatus(SignDec.STATUS_UN_DELETED);

        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>(userInfo);
        List<UserInfo> userInfos = userInfoMapper.selectList(wrapper);
       Long userId= null;
        if (userInfos.size()  == 0){
            //用户不存在就创建一个用户
            UserInfo copy = CopyUtils.copy(vo, UserInfo.class);
            copy.setCreateuser(String.valueOf(accountId));
            copy.setCreatetime(new Date());
            copy.setStatus(SignDec.STATUS_UN_DELETED);
            userInfoMapper.insert(copy);
            userId = copy.getId();
        }else{
            userId = userInfos.get(0).getId();
        }

        //创建报名绑定信息
        //先查询报名基本信息
        SignInfo signInfo = new SignInfo();
        signInfo.setId(vo.getSignInfoId());
        signInfo.setStatus(SignDec.STATUS_UN_DELETED);
        QueryWrapper<SignInfo> wrapper1 = new QueryWrapper<>(signInfo);
        List<SignInfo> signInfos = signInfoMapper.selectList(wrapper1);
        if (signInfos.size() == 0){
            return  ResultBean.builder().statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg(FailStatusMsg.CREATE_EXIST_DATA).build();
        }
        UserSignInfo userSignInfo = CopyUtils.copy(signInfos.get(0), UserSignInfo.class);
        userSignInfo.setSignId(vo.getSignInfoId());
        userSignInfo.setCreateTime(new Date());
        userSignInfo.setUpdateTime(new Date());
        userSignInfo.setUserId(userId);
        userSignInfoMapper.insert(userSignInfo);

        //绑定 用户 和分组的关系
        UserGroupBind userGroupBind = new UserGroupBind();
        userGroupBind.setUserId(userId);
        userGroupBind.setGroupId(signInfos.get(0).getGroupId());
        userGroupBind.setStatus(SignDec.STATUS_UN_DELETED);
        userGroupBind.setAccountId(accountId);
        userGroupBindMapper.insert(userGroupBind);
        return  ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).build();



    }
}
