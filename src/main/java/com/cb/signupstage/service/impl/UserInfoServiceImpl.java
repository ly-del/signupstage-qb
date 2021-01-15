package com.cb.signupstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.cb.signupstage.service.UserGroupBindService;
import com.cb.signupstage.service.UserInfoService;
import com.cb.signupstage.utils.CopyUtils;
import com.cb.signupstage.vo.UserSelectPageVo;
import com.cb.signupstage.vo.UserSignBindVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: ly
 * @time: 2020/11/11 9:38
 * @description: 考生报名 业务层
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

    @Autowired
    private UserGroupBindService userGroupBindService;




    /**
     * 查询 自定义信息 列表
     */
    public List getCustomizeList(UserCustomize customize) {
        List<UserCustomize> customizeList = userCustomizeMapper.getCustomizeList(customize);
        return customizeList;
    }


    public ResultBean saveUserInfo(UserInfoPageDTO userInfoDTO, Long accountId) {
        //姓名和手机号必填
        if (ObjectUtils.isEmpty(userInfoDTO.getMobile()) || ObjectUtils.isEmpty(userInfoDTO.getUserName()) ||
                ObjectUtils.isEmpty(userInfoDTO.getGroupIds())
        ) {
            return ResultBean.builder().result(false).statusCode(StatusCode.SUCCESS_CODE).failMsg("手机号、姓名、分组不能为空").build();
        }

        if (ObjectUtils.isEmpty(userInfoDTO.getId())) {

            //先查询这个手机号是否已经注册

            UserInfo select = new UserInfo();
            select.setMobile(userInfoDTO.getMobile());
            select.setStatus(SignDec.STATUS_UN_DELETED);
            List<UserInfo> userList = userInfoMapper.selectBySelect(select);
            if (userList.size() > 0) {
                return ResultBean.builder().result(false).statusCode(StatusCode.SUCCESS_CODE).failMsg("该手机号已注册").build();
            }
            UserInfo userInfo = new UserInfo();

            UserInfo copy = CopyUtils.copy(userInfoDTO, UserInfo.class);
            copy.setStatus(1);
            userInfoMapper.insert(copy);

            List<UserGroupBind> saveBean = new ArrayList<>();
            //用户分组关系
            for (Long groupId : userInfoDTO.getGroupIds()) {
                UserGroupBind userGroupBind = new UserGroupBind();
                userGroupBind.setUserId(copy.getId());
                userGroupBind.setAccountId(accountId);
                userGroupBind.setCreateTime(LocalDateTime.now());
                userGroupBind.setGroupId(groupId);
                userGroupBind.setDeleted(SignDec.deletedType.UN_DELETED.getCode());
                userGroupBind.setUpdateTime(LocalDateTime.now());
                saveBean.add(userGroupBind);
            }

            //添加用户分组关系表数据
            userGroupBindService.saveBatch(saveBean);

            return ResultBean.builder().result(true).statusCode(StatusCode.SUCCESS_CODE).failMsg("保存成功").build();
        }

        //修改

        //判断手机号是否重复

        UserInfo select = new UserInfo();
        select.setMobile(userInfoDTO.getMobile());
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>(select);
        List<UserInfo> userList = userInfoMapper.selectList(wrapper);

        if (userList.size() > 0) {
            if (!userInfoDTO.getId().equals(userList.get(0).getId())) {
                //手机号已被注册
                return ResultBean.builder().result(false).statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg("该手机号已注册").build();

            }
        }

        UserInfo copy = CopyUtils.copy(userInfoDTO, UserInfo.class);


        int i = userInfoMapper.updateById(copy);

        if (i < 1) {
            return ResultBean.builder().result(false).statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg(FailStatusMsg.SYSTEM_EXCEPTION).build();
        }
        //先查出已经在的 考生与分组绑定关系  根据考生id 查询
        List<Long> groupIds = userGroupBindMapper.selectGroupIds(copy.getId());
        //交集  传过来有 数据库 也有 就修改状态为正常  因为 查询 数据库数据是查全部 没有根据状态条件查询,
        // 在原有数据 上进行操作 无法根据状态条件查询
        Collection<Long> intersection = CollectionUtils.intersection(userInfoDTO.getGroupIds(), groupIds);
        //差集 传过来有 数据库没有  就新增
        Collection<Long> addSubtract = CollectionUtils.subtract(userInfoDTO.getGroupIds(), groupIds);
        //差集 传过来 没有 数据库有  就删除 改变状态为 已删除
        Collection<Long> delSubtract = CollectionUtils.subtract(groupIds, userInfoDTO.getGroupIds());
        if (intersection.size() > 0) {
            //更改状态  为 正常
            LambdaUpdateWrapper<UserGroupBind> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.in(UserGroupBind::getUserId, userInfoDTO.getId())
                    .in(UserGroupBind::getGroupId, intersection)
                    .set(UserGroupBind::getDeleted, SignDec.deletedType.UN_DELETED.getCode());
            userGroupBindMapper.update(null, lambdaUpdateWrapper);
        }
        if (addSubtract.size() > 0) {
            List<UserGroupBind> addBean = new ArrayList<>();
            //新增
            for (Long groupId : addSubtract) {
                UserGroupBind userGroupBind = new UserGroupBind();
                userGroupBind.setCreateTime(LocalDateTime.now());
                userGroupBind.setUpdateTime(LocalDateTime.now());
                userGroupBind.setGroupId(groupId);
                userGroupBind.setUserId(userInfoDTO.getId());
                userGroupBind.setDeleted(SignDec.deletedType.UN_DELETED.getCode());
                userGroupBind.setAccountId(accountId);
                addBean.add(userGroupBind);
            }
            userGroupBindService.saveBatch(addBean);
        }
        if (delSubtract.size() > 0) {
            //更改状态  为 已删除
            LambdaUpdateWrapper<UserGroupBind> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.in(UserGroupBind::getUserId, userInfoDTO.getId())
                    .in(UserGroupBind::getGroupId, delSubtract)
                    .set(UserGroupBind::getDeleted, SignDec.deletedType.DELETED.getCode());
            userGroupBindMapper.update(null, lambdaUpdateWrapper);
        }


        return ResultBean.builder().result(true).statusCode(StatusCode.SUCCESS_CODE).failMsg("修改成功").build();
    }

    /**
     * 用户删除
     *
     * @param userInfo
     * @return
     */
    public ResultBean deleteUserInfo(UserInfo userInfo) {
        userInfo.setStatus(0);
        int i = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if (i == 0) {
            return ResultBean.builder().result(false).statusCode(StatusCode.SUCCESS_CODE).failMsg(FailStatusMsg.SYSTEM_EXCEPTION).build();
        }
        return ResultBean.builder().result(true).statusCode(StatusCode.SUCCESS_CODE).failMsg("删除成功").build();
    }


    public IPage<UserInfoPageDTO> pageQuery(Page<UserInfo> page, UserSelectPageVo vo, Long accountId) {

        //查询列表
        IPage<UserInfoPageDTO> userInfos = userInfoMapper.pageQuery(page, vo, accountId);

        if (userInfos.getSize() > 0) {
            for (UserInfoPageDTO userInfoPageDTO : userInfos.getRecords()) {
                List<String> groupIds = Arrays.asList(userInfoPageDTO.getGroupId().split(","));
                List<Long> collect = groupIds.stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
                userInfoPageDTO.setGroupIds(collect);
            }
        }
        return userInfos;
    }

    @Override
    public void moveUserToGroup(List<Long> groupIds, List<Long> userIds, Long accountId) {
        List<UserGroupBind> getList = new ArrayList<>();
        for (Long gId : groupIds) {
            for (Long uId : userIds) {
                UserGroupBind userGroupBind = new UserGroupBind();
                userGroupBind.setUserId(uId);
                userGroupBind.setGroupId(gId);
                getList.add(userGroupBind);
            }
        }
        // 根据考生查询目前所在的分组
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in("user_id", userIds);

        List<UserGroupBind> list = userGroupBindMapper.selectList(wrapper);
        //差集 传过来没有 但数据库有
        List<UserGroupBind> list2 = list.stream()
                .filter(item -> !getList.stream().map(e -> e.getUserId() + "&" + e.getGroupId()).collect(Collectors.toList()).contains(item.getUserId() + "&" + item.getGroupId()))
                .collect(Collectors.toList());
        System.out.println(list2.toArray());
        //改变绑定的分组 状态 为 2
        if (list2.size() > 0) {
            for (UserGroupBind userGroupBind : list2) {
                userGroupBind.setDeleted(SignDec.deletedType.DELETED.getCode());
                userGroupBind.setUpdateTime(LocalDateTime.now());
            }
            userGroupBindService.updateBatchById(list2);
        }
        //差集 传过来的 有 而数据库没有
        List<UserGroupBind> list1 = getList.stream()
                .filter(item -> !list.stream().map(e -> e.getUserId() + "&" + e.getGroupId()).collect(Collectors.toList()).contains(item.getUserId() + "&" + item.getGroupId()))
                .collect(Collectors.toList());
        System.out.println(list1.toArray());
        //新增绑定的状态
        if (list1.size() > 0) {
            for (UserGroupBind userGroupBind : list1) {
                userGroupBind.setCreateTime(LocalDateTime.now());
                userGroupBind.setUpdateTime(LocalDateTime.now());
                userGroupBind.setAccountId(accountId);
                userGroupBind.setDeleted(SignDec.deletedType.UN_DELETED.getCode());
            }
            //批量新增
            userGroupBindService.saveBatch(list1);
        }

        //相同 改变状态
        List<UserGroupBind> list3 = list.stream()
                .filter(item -> getList.stream().map(e -> e.getUserId() + "&" + e.getGroupId()).collect(Collectors.toList()).contains(item.getUserId() + "&" + item.getGroupId()))
                .collect(Collectors.toList());

        if (list3.size() > 0) {
            for (UserGroupBind userGroupBind : list3) {
                userGroupBind.setDeleted(SignDec.deletedType.UN_DELETED.getCode());
                userGroupBind.setUpdateTime(LocalDateTime.now());
            }
            userGroupBindService.updateBatchById(list3);
        }

    }


    public ResultBean userInfoBind(UserSignBindVo vo, Long accountId) {

        SignInfo signInfo = new SignInfo();
        signInfo.setId(vo.getSignInfoId());
        signInfo.setDeleted(SignDec.deletedType.UN_DELETED.getCode());
        QueryWrapper<SignInfo> wrapper1 = new QueryWrapper<>(signInfo);
        List<SignInfo> signInfos = signInfoMapper.selectList(wrapper1);
        if (signInfos.size() == 0) {
            return ResultBean.builder().result(false).statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg("报名信息不存在").build();
        }
        //报名之前 先查询 是否已经报过名 (目前是手机号做唯一识别)  存在
        List<UserSignInfo> bindList = userSignInfoMapper.getExist(vo);
        if (bindList.size() > 0) {
            //已经存在当前报名的 报名信息记录 不能重复报名
            return ResultBean.builder().result(false).statusCode(StatusCode.SYSTEM_EXCEPTION_CODE).failMsg("不能重复报名").build();
        }
        //创建报名信息
        //先创建用户信息 存在就不创建
        UserInfo userInfo = new UserInfo();
        userInfo.setMobile(vo.getMobile());
        userInfo.setStatus(SignDec.STATUS_UN_DELETED);
        if (!ObjectUtils.isEmpty(vo.getCustomInformation())) {
            String customInformation = String.valueOf(vo.getCustomInformation());
            userInfo.setCustomInformation(customInformation);
        }

        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>(userInfo);
        List<UserInfo> userInfos = userInfoMapper.selectList(wrapper);
        Long userId = null;
        if (userInfos.size() <= 0) {
            //用户不存在就创建一个用户
            UserInfo copy = CopyUtils.copy(vo, UserInfo.class);
            copy.setUserName(vo.getUserName());
            copy.setCreateUser(String.valueOf(accountId));
            copy.setStatus(SignDec.STATUS_UN_DELETED);
            userInfoMapper.insert(copy);
            userId = copy.getId();
        } else {
            userId = userInfos.get(0).getId();
        }

        //创建报名绑定信息
        //先查询报名基本信息

        UserSignInfo userSignInfo = CopyUtils.copy(signInfos.get(0), UserSignInfo.class);
        userSignInfo.setSignId(vo.getSignInfoId());
        userSignInfo.setUserId(userId);
        userSignInfoMapper.insert(userSignInfo);

        //绑定 用户 和分组的关系
        UserGroupBind userGroupBind = new UserGroupBind();
        userGroupBind.setUserId(userId);
        userGroupBind.setGroupId(signInfos.get(0).getGroupId());
        userGroupBind.setDeleted(SignDec.deletedType.UN_DELETED.getCode());
        userGroupBind.setAccountId(accountId);
        userGroupBindMapper.insert(userGroupBind);
        return ResultBean.builder().result(true).statusCode(StatusCode.SUCCESS_CODE).build();


    }

    @Override
    public UserInfoPageDTO getUserById(Long id) {
        return userInfoMapper.getById(id);
    }


}
