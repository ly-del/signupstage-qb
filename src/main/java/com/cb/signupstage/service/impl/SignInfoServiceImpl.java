package com.cb.signupstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.common.SignDec;
import com.cb.signupstage.common.StatusCode;
import com.cb.signupstage.dto.PagedResult;
import com.cb.signupstage.dto.SignInfoFormDTO;
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
import com.cb.signupstage.vo.SignInfoSaveVo;
import com.cb.signupstage.vo.SignInfoVo;
import com.cb.signupstage.vo.UserSearchVo;
import com.cb.signupstage.vo.UserSignSearchVo;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private SignInfoFormService signInfoFormService;



    //第一次 新建报名  只会触发一次
    @Override
    public ResultBean saveFirst(SignInfo signInfo, Long accountId) {
        //查询在不在
        SignInfo select = new SignInfo();
        select.setName(signInfo.getName());
        QueryWrapper wrapper =new QueryWrapper(select);
        List list = signInfoMapper.selectList(wrapper);
        if (list.size()>0){
            return  ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).result(false).failMsg("报名标题已存在").build();
        }
        signInfo.setImage("https://cbpublic.oss-cn-shenzhen.aliyuncs.com/exam-resources/exam_default_image.png");
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
        signInfo.setIsRelease(SignDec.RELEASE_NOT_RELEASE);
        int insert = signInfoMapper.insert(signInfo);
        if (insert < 0){
            //失败 return
         return   ResultBean.builder().statusCode(StatusCode.SUCCESS_CODE).result(false).failMsg("保存失败").build();
        }

        // 第一次新建报名 自动增加两条 固定属性 记录
        saveInfoSetting(signInfo.getId(), accountId);


        List<SignInfoFormDTO> formList = signInfoFormService.getFormList(signInfo.getId());

        List<SignInfoFormDTO> sortList = formList.stream().sorted(Comparator.comparing(SignInfoFormDTO::getSort)).collect(Collectors.toList());


        SignInfoSaveVo signInfoSaveVo =CopyUtils.copy(signInfo,SignInfoSaveVo.class);
        signInfoSaveVo.setFormList(sortList);


        return ResultBean.builder().data(signInfoSaveVo).statusCode(StatusCode.SUCCESS_CODE).result(true).failMsg(null).build();
    }



    @Override
    public String saveOrCopy(Long id ,String name,Long accountId) {

        //存在 就 复制 一个新的
        //先查找 已存在的报告的信息
        SignInfo info = signInfoMapper.selectById(id);
        if (ObjectUtils.isEmpty(info)){
            return "报名信息为空，复制失败";
        }
        //判断 新加的名字 是否已存在
        QueryWrapper selectWrapper = new QueryWrapper();
        selectWrapper.eq("name",name);
        selectWrapper.eq("deleted",0);
        List list = signInfoMapper.selectList(selectWrapper);
        if (list.size() > 0){
            return "报名标题已存在,不能重复";
        }

        SignInfo copy = CopyUtils.copy(info, SignInfo.class);
        copy.setId(null);
        copy.setName(name);
        copy.setCreateTime(LocalDateTime.now());
        copy.setUpdateTime(LocalDateTime.now());
        signInfoMapper.insert(copy);

        SignInfoForm signInfoForm = new SignInfoForm();
        signInfoForm.setSignInfoId(id);
        QueryWrapper<SignInfoForm> wrapper = new QueryWrapper<>(signInfoForm);
        List<SignInfoForm> signInfoForms = signInfoFormMapper.selectList(wrapper);
        for (SignInfoForm infoForm : signInfoForms) {
            infoForm.setId(null);
            infoForm.setSignInfoId(copy.getId());
            infoForm.setAccountId(accountId);
            infoForm.setCreateTime(LocalDateTime.now());
        }
        boolean b = signInfoFormService.saveBatch(signInfoForms);
        if (!b){
            return "复制报名失败";
        }

        return null;
    }

    //第一次新增发  设置基础报名页面 属性字段信息
    public void saveInfoSetting(Long signInfoId ,Long accountId){

       List<SignInfoForm> formList = new ArrayList<>();
        SignInfoForm signInfoForm = new SignInfoForm();
        signInfoForm.setSignInfoId(signInfoId);
        signInfoForm.setAccountId(accountId);
        signInfoForm.setCustomizeId(Long.valueOf(1));
        signInfoForm.setSort(1);
        signInfoForm.setDeleted(SignDec.deletedType.UN_DELETED.getCode());
        formList.add(signInfoForm);

        SignInfoForm signInfoForm2 = new SignInfoForm();
        signInfoForm2.setSignInfoId(signInfoId);
        signInfoForm2.setAccountId(accountId);
        signInfoForm2.setCustomizeId(Long.valueOf(2));
        signInfoForm2.setSort(2);
        signInfoForm2.setDeleted(SignDec.deletedType.UN_DELETED.getCode());
        formList.add(signInfoForm2);
        signInfoFormService.saveBatch(formList);
    }

    @Override
    public SignInfoPageDTO queryPage(Page<SignInfo> page, Long accountId) {

        //先查询所有的  报名基础性信息
        SignInfo signInfo = new SignInfo();
        signInfo.setDeleted(SignDec.deletedType.UN_DELETED.getCode());
        QueryWrapper<SignInfo> wrapper = new QueryWrapper<>(signInfo);
        wrapper.orderByDesc("create_time");
        IPage<SignInfoVo> signInfoList = signInfoMapper.selectPageList(page, wrapper);


        //查询本周报名人数 和 本月报名人数 和报名总收益
        //本周
    //  int week =  signInfoMapper.selectThisWeekCount();
        //本月
    //  int month =  signInfoMapper.selectThisMonthCount();
      //查询收益

        SignInfoPageDTO dto = signInfoMapper.selectCountAndCost();
       // PagedResult<SignInfoVo> signInfoVoPagedResult = new PagedResult<>(signInfoList);
        dto.setPagedResult(signInfoList);
        return dto;

    }







    @Override
    public IPage<UserSearchVo> queryUserSignPage(Page<SignInfo> page, UserSearchVo vo, Long accountId) {

        //先查询所有的  报名基础性信息
        IPage<UserSearchVo> userSignList = userSignInfoMapper.selectPageList(page,vo,accountId);

        return userSignList;
    }

    @Override
    public List<UserSignSearchVo> querySignPage( UserSignSearchVo vo) {
       // PageHelper.startPage(page.getPageNum(), page.getPageSize());
        //先查询所有的  报名基础性信息

        List<UserSignSearchVo> userSignList = userSignInfoMapper.selectExportPageList(vo);

        return userSignList;
    }




}
