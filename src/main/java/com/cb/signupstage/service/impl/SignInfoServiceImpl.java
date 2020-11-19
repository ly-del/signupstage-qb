package com.cb.signupstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.common.SignDec;
import com.cb.signupstage.common.StatusCode;
import com.cb.signupstage.dto.PagedResult;
import com.cb.signupstage.dto.SignInfoPageDTO;
import com.cb.signupstage.entity.SignInfo;
import com.cb.signupstage.entity.SignInfoForm;
import com.cb.signupstage.mapper.SignInfoFormMapper;
import com.cb.signupstage.mapper.SignInfoMapper;
import com.cb.signupstage.mapper.UserSignInfoMapper;
import com.cb.signupstage.service.SignInfoFormService;
import com.cb.signupstage.service.SignInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cb.signupstage.utils.CopyUtils;
import com.cb.signupstage.vo.SignInfoVo;
import com.cb.signupstage.vo.UserSignSearchVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报名信息表 服务实现类
 * </p>
 *
 * @author MyBatisPlusGenerater
 * @since 2020-11-13
 */
@Service
public class SignInfoServiceImpl  extends ServiceImpl<SignInfoMapper, SignInfo> implements SignInfoService {

    @Autowired
    private SignInfoMapper signInfoMapper;

    @Autowired
    private SignInfoFormMapper signInfoFormMapper;

    @Autowired
    private UserSignInfoMapper userSignInfoMapper;


    @Override
    public ResultBean saveOrCopy(SignInfoVo vo ,Long accountId) {

        if (ObjectUtils.isEmpty(vo.getId())){
            //不存在 新建 一个 报告信息
            SignInfo signInfo = new SignInfo();
            signInfo.setName("报名标题");
            signInfo.setCost(SignDec.COST);
            signInfo.setMaxTotal(SignDec.MAX_TOTAL);
            signInfo.setAccountId(accountId);
            signInfo.setCreateTime(LocalDateTime.now());
            signInfo.setUpdateTime(LocalDateTime.now());
            signInfo.setIsSkip(1);
            signInfo.setUnstartedDec(SignDec.UNSTARTED_DEC);
            signInfo.setCompleteDec(SignDec.COMPLETE_DEC);
            signInfo.setSucceedDec(SignDec.SUCCEED_DEC);
            signInfo.setFailDec(SignDec.FAIL_DEC);
            signInfo.setShareDec(SignDec.SHARE_DEC);
           signInfo.setRelease(SignDec.RELEASE_NOT_RELEASE);

            signInfoMapper.insert(signInfo);

            //保存 报名页面基础信息设置
            saveInfoSetting(signInfo.getId(),accountId);

            return ResultBean.builder().data(signInfo).statusCode(StatusCode.SUCCESS_CODE).failMsg(null).build();
        }

        //存在 就 复制 一个新的
        //先查找 已存在的报告的信息
        SignInfo info = signInfoMapper.selectById(vo.getId());

        SignInfo copy = CopyUtils.copy(info, SignInfo.class);
        copy.setId(null);
        copy.setName(copy.getName()+"(副本)");
        copy.setCreateTime(LocalDateTime.now());
        copy.setUpdateTime(LocalDateTime.now());
        signInfoMapper.insert(copy);

        saveInfoSetting(info.getId(),accountId);
        return ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).failMsg(null).build();
    }

    //设置基础报名页面 字段信息
    public void saveInfoSetting(Long signInfoId ,Long accountId){

        SignInfoForm signInfoForm = new SignInfoForm();
        signInfoForm.setSignInfoId(signInfoId);
        signInfoForm.setAccountId(accountId);
        signInfoForm.setCreateTime(LocalDateTime.now());
        signInfoForm.setUpdateTime(LocalDateTime.now());
        signInfoForm.setStatus(SignDec.STATUS_UN_DELETED);
        signInfoForm.setName("姓名");
        signInfoForm.setMobile("手机");
        signInfoFormMapper.insert(signInfoForm);
    }

    @Override
    public SignInfoPageDTO queryPage(Page page, Long accountId) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        //先查询所有的  报名基础性信息
        SignInfo signInfo = new SignInfo();
        signInfo.setStatus(SignDec.STATUS_UN_DELETED);
        QueryWrapper<SignInfo> wrapper = new QueryWrapper<>(signInfo);
        List<SignInfoVo> signInfoList = signInfoMapper.selectPageList(wrapper);

        //查询本周报名人数 和 本月报名人数 和报名总收益
        //本周
    //  int week =  signInfoMapper.selectThisWeekCount();
        //本月
    //  int month =  signInfoMapper.selectThisMonthCount();
      //查询收益

        SignInfoPageDTO dto = signInfoMapper.selectCountAndCost();
        PagedResult<SignInfoVo> signInfoVoPagedResult = new PagedResult<>(signInfoList);
        dto.setPagedResult(signInfoVoPagedResult);
        return dto;

    }







    @Override
    public List<UserSignSearchVo> queryUserSignPage(Page<SignInfo> page, UserSignSearchVo vo, Long accountId) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        //先查询所有的  报名基础性信息

        List<UserSignSearchVo> userSignList = userSignInfoMapper.selectPageList(vo,accountId);

        return userSignList;
    }



}
