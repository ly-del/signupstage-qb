package com.cb.signupstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cb.signupstage.common.FailStatusMsg;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.common.SignDec;
import com.cb.signupstage.common.StatusCode;
import com.cb.signupstage.entity.SignInfo;
import com.cb.signupstage.entity.UserCustomize;
import com.cb.signupstage.mapper.SignInfoMapper;
import com.cb.signupstage.mapper.UserCustomizeMapper;
import com.cb.signupstage.service.SignInfoService;
import com.cb.signupstage.service.UserCustomizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * @author: ly
 * @time: 2020/12/22 13:43
 * @description:
 */
@Slf4j
@Service
public class UserCustomizeServiceImpl  extends ServiceImpl<UserCustomizeMapper, UserCustomize> implements UserCustomizeService {

    @Autowired
    private UserCustomizeMapper userCustomizeMapper;

    /**
     * 保存 修改 自定义类信息
     *
     * @param
     * @return
     */
    public ResultBean saveCustomize(Map<String, String> map, Long accountId) {
        String customize = map.get("customize");

        UserCustomize userCustomize = new UserCustomize();
        if (ObjectUtils.isEmpty(map.get("id"))) {
            //先查询 如果已存在 则新建失败
            userCustomize.setName(customize);
            QueryWrapper<UserCustomize> wrapper = new QueryWrapper<>();
            wrapper.eq("name",customize);
            List customizeList = userCustomizeMapper.selectList(wrapper);

            if (customizeList.size() > 0) {
                //存在 则创建失败
                return ResultBean.builder().result(false).data(customize).statusCode(StatusCode.SUCCESS_CODE).failMsg(FailStatusMsg.CREATE_EXIST_DATA).build();
            }
            userCustomize.setType(2);
            userCustomize.setIsMust(SignDec.IS_MUST_NO);
            userCustomize.setAccountId(accountId);

            userCustomizeMapper.insert(userCustomize);
            return ResultBean.builder().result(true).statusCode(StatusCode.SUCCESS_CODE).failMsg("保存成功").build();
        }

        //修改
        userCustomize.setId(Long.valueOf(map.get("id")));
        userCustomize.setName(customize);
        userCustomizeMapper.updateById(userCustomize);

        return ResultBean.builder().result(true).statusCode(StatusCode.SUCCESS_CODE).failMsg("修改成功成功").build();

    }


    /**
     * 删除 自定义信息 列表
     */
    public int deleteUserCustomize(Long id) {
        UserCustomize userCustomize = new UserCustomize();
        userCustomize.setId(id);
        userCustomize.setDeleted(1);
        return userCustomizeMapper.updateById(userCustomize);
    }

}
