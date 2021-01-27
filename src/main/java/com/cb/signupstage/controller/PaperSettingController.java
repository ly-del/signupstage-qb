package com.cb.signupstage.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.common.SignDec;
import com.cb.signupstage.common.StatusCode;
import com.cb.signupstage.dto.*;
import com.cb.signupstage.entity.*;
import com.cb.signupstage.service.*;
import com.cb.signupstage.utils.ExcelUtil;
import com.cb.signupstage.vo.PaperInterviewSettingSearchVo;
import com.cb.signupstage.vo.PaperSettingPageSearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.Get;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 论文分组配置表 前端控制器
 * </p>
 *
 * @author ly
 * @since 2020-12-23
 */
@Api(tags = "论文设置模块")
@RestController
@Slf4j
@RequestMapping("/paper-setting/v1")
public class PaperSettingController {

    @Autowired
    private PaperSettingService paperSettingService;

    @Autowired
    private PaperReviewService paperReviewService;

    @Autowired
    private PaperUploadRecordService paperUploadRecordService;

    @Autowired
    private PaperInterviewSettingService paperInterviewSettingService;

    @Autowired
    private PaperInterviewUserRelationService paperInterviewUserRelationService;

    @Autowired
    private PaperInterviewScoreService paperInterviewScoreService;

    @Autowired
    private PaperScoreSettingService paperScoreSettingService;

    @Value("${dataBase}")
    private String dataBase;


    @ApiOperation("添加/修改 论文分组")
    @PostMapping("/save/editPaperSetting")
    public ResultBean save(@RequestBody PaperSetting paperSetting, @RequestHeader Long accountId) {
        log.info("paper setting request.{}", paperSetting);

        //新增 查询是否已存在
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("test_id", paperSetting.getTestId());
        wrapper.eq("paper_group_name", paperSetting.getPaperGroupName());
        List<PaperSetting> list = paperSettingService.list(wrapper);
        if (ObjectUtils.isEmpty(paperSetting.getId())) {
            if (list.size() > 0) {
                //新增
                return ResultBean.failure("分组已存在");
            }
            paperSetting.setAccountId(accountId);
        }

        //修改
        if (list.size() > 0) {

            boolean b = paperSetting.getId().equals(list.get(0).getId());
            if (!b) {
                return ResultBean.failure("分组已存在");
            }
        }
        boolean b = paperSettingService.saveOrUpdate(paperSetting);
        return new ResultBean(200, null, b, null);
    }


    @ApiOperation("删除 论文分组")
    @PostMapping(value = "/delete/delPaperSetting")
    public ResultBean deletePaperSetting(
            @RequestBody Map<String, String> map, @RequestHeader Long accountId) {
        Long id = Long.valueOf(map.get("id"));
        PaperSetting paperSetting = new PaperSetting();
        paperSetting.setId(id);
        paperSetting.setDeleted(SignDec.deletedType.DELETED.getCode());
        boolean b = paperSettingService.updateById(paperSetting);

        return new ResultBean<>(StatusCode.SUCCESS_CODE, null, b, null);
    }

    @ApiOperation("分页查询论文分组设置")
    @PostMapping(value = "/query/paperSettingPage")
    public ResultBean queryPaperPage(
            @RequestBody PaperSettingPageSearchVo vo, @RequestHeader Long accountId) {


        Page<T> pagebean = paperSettingService.queryPaperPage(new Page<>(vo.getJumpPage(), vo.getPageSize()), vo, dataBase);
        return new ResultBean<>(StatusCode.SUCCESS_CODE, null, true, pagebean);
    }

    @ApiOperation("论文分组详情")
    @PostMapping(value = "/query/queryPaperSettingDetail")
    public ResultBean queryPaperSettingDetail(
            @RequestBody Map<String, String> map) {
        String id = map.get("id");

        PaperSettingDetailDTO detail = paperSettingService.queryPaperSettingDetail(id, dataBase);
        return ResultBean.success(detail, null);
    }

    @ApiOperation("查询论文分组list")
    @PostMapping(value = "/query/paperSettingList")
    public ResultBean queryPaperSettingList(@RequestBody Map<String,String> map,
            @RequestHeader Long accountId) {
        if (ObjectUtils.isEmpty(map.get("source"))){
            return ResultBean.failure("报名来源不能为空");
        }
        Long source = Long.valueOf(map.get("source"));
        QueryWrapper<PaperSetting> wrapper = new QueryWrapper();
        wrapper.eq("deleted", SignDec.deletedType.UN_DELETED.getCode())
        .eq("source",source);
        List list = paperSettingService.list(wrapper);
        return new ResultBean<>(StatusCode.SUCCESS_CODE, null, true, list);
    }

    @ApiOperation("论文审核记录分页列表")
    @PostMapping(value = "/query/pageReviewRecordList")
    public ResultBean queryPaperReviewPage(
            @RequestBody PaperSettingPageSearchVo vo, @RequestHeader Long accountId) {

        Page<PaperReviewPageDTO> pagebean = paperReviewService.queryPaperReviewPage(new Page<>(vo.getJumpPage(), vo.getPageSize()), vo, dataBase);
        return new ResultBean<>(StatusCode.SUCCESS_CODE, null, true, pagebean);
    }


    @ApiOperation("批量导出 审核记录")
    @PostMapping(value = "/export/pageReviewRecordList")
    public void exportPageReviewRecordList(HttpServletResponse response, @RequestParam String ids) {

        String fileName = "审核记录列表";

        // 自定义Excel文件名
        String excelName = fileName;

        List<PaperReviewExportDTO> dto = paperReviewService.exportPageReviewRecordList(ids);

        // easyexcel工具类实现Excel文件导出
        ExcelUtil.writeExcel(response, dto, fileName, excelName, new PaperReviewExportDTO());

    }

    @ApiOperation("我的论文分页列表")
    @PostMapping("/query/paperUploadPage")
    public ResultBean queryPaperUploadPage(@RequestBody Map<String, Integer> map, @RequestHeader Long accountId) {

        Page<PaperUploadPageDTO> pageBean = paperUploadRecordService.
                queryPaperUploadPage(new Page<>(map.get("jumpPage"), map.get("pageSize")), Long.valueOf(map.get("userId")));
        return new ResultBean<>(StatusCode.SUCCESS_CODE, null, true, pageBean);
    }


    @ApiOperation("单个论文审核")
    @PostMapping(value = "/review/onePaper")
    public ResultBean reviewPaper(@RequestBody PaperReview paperReview, @RequestHeader Long accountId) {

        boolean b = paperReviewService.saveOrUpdate(paperReview);
        if (b) {
            //同步更新论文上传记录表的 审核状态
            PaperUploadRecord paperUploadRecord = new PaperUploadRecord();
            paperUploadRecord.setId(paperReview.getPaperId());
            paperUploadRecord.setReviewStatus(paperReview.getReviewStatus());
            paperUploadRecordService.updateById(paperUploadRecord);
        }
        return new ResultBean<>(StatusCode.SUCCESS_CODE, null, b, null);
    }

    @ApiOperation("批量论文审核")
    @PostMapping(value = "/review/paperBanth")
    public ResultBean reviewPaper(@RequestBody Map<String, Object> map, @RequestHeader Long accountId) {

        List<Map<String,String>> listMaps = (List<Map<String,String>>) map.get("ids");
        Integer reviewStatus = (Integer) map.get("reviewStatus");
        List<String> idStr = listMaps.stream().map(p -> p.get("id")).collect(Collectors.toList());
        List<Long> idLong = idStr.stream().map(p -> Long.parseLong(p)).collect(Collectors.toList());
        LambdaUpdateWrapper<PaperUploadRecord> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.in(PaperUploadRecord::getId, idLong).set(PaperUploadRecord::getReviewStatus, reviewStatus);
        boolean b = paperUploadRecordService.update(null, lambdaUpdateWrapper);
        boolean result = false;
        if (b) {
//            String[] split = ids.split(",");
//
//            List<Long> collect = Arrays.stream(split)
//                    .map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
            List<PaperReview> saveList = new ArrayList<>();
            for (Map<String,String> idMap : listMaps) {
                PaperReview paperReview = new PaperReview();
                paperReview.setPaperId(Long.valueOf(idMap.get("id")));
                paperReview.setUserId(Long.valueOf(idMap.get("userId")));
                paperReview.setReviewStatus(SignDec.paperReviewStatus.values()[reviewStatus]);
                saveList.add(paperReview);
            }
            if (reviewStatus.equals(1)) {
                saveList.forEach(e -> {
                    e.setReviewDec("审核通过，祝你一切顺利");
                });
            }
            saveList.forEach(e -> {
                e.setReviewDec("审核不通过");
            });

            if (saveList.size() > 0) {
                //同步更新审核记录 到 审核记录表里
                result = paperReviewService.saveBatch(saveList);
            }
        }
        return new ResultBean<>(StatusCode.SUCCESS_CODE, null, result, null);
    }

    @ApiOperation("论文审核详情")
    @PostMapping(value = "/query/paperReviewDetail")
    public ResultBean getPaperReviewDetail(@RequestBody PaperReview paperReview, @RequestHeader Long accountId) {

        PaperReviewDetailDTO detailDTO = paperReviewService.getPaperReviewDetail(paperReview.getId(), dataBase);
        return ResultBean.success(detailDTO, null);
    }

    @ApiOperation("新增、编辑预约时间段")
    @PostMapping(value = "/saveOrUpdate/interviewSetting")
    public ResultBean saveOrUpdateInterviewSetting(@RequestBody PaperInterviewSetting paperInterviewSetting, @RequestHeader Long accountId) {

        //先查询有没有重复的 的记录
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("paper_group_id", paperInterviewSetting.getPaperGroupId());
        wrapper.eq("booked_date", paperInterviewSetting.getBookedDate());
        wrapper.eq("start_time", paperInterviewSetting.getStartTime());
        wrapper.eq("end_time", paperInterviewSetting.getEndTime());
        List<PaperInterviewSetting> list = paperInterviewSettingService.list(wrapper);
        //新增
        if (ObjectUtils.isEmpty(paperInterviewSetting.getId())) {
            if (list.size() > 0) {
                //该分组的预约时间段 已经存在 不能重复添加
                return ResultBean.failure("当前分组的预约时间段已经存在,不能重复添加");
            }
            //根据日期判断  时间段是否存在交叉
            QueryWrapper dateWraaper = new QueryWrapper();
            dateWraaper.eq("paper_group_id", paperInterviewSetting.getPaperGroupId());
            dateWraaper.eq("booked_date", paperInterviewSetting.getBookedDate());
            List<PaperInterviewSetting> dateList = paperInterviewSettingService.list(dateWraaper);
            PaperInterviewSetting dateVo = new PaperInterviewSetting();
            dateVo.setStartTime(paperInterviewSetting.getStartTime());
            dateVo.setEndTime(paperInterviewSetting.getEndTime());
            dateList.add(dateVo);
            Collections.sort(dateList, Comparator.comparing(PaperInterviewSetting::getStartTime));
            for (int i = 0; i < dateList.size() - 1; i++) {
                for (int j = i + 1; j < dateList.size(); j++) {
                    DateFormat df = new SimpleDateFormat("HH:mm:ss");
                    try {
                        if ((df.parse(dateList.get(i).getStartTime())).getTime() < (df.parse(dateList.get(j).getEndTime())).getTime() &&
                                (df.parse(dateList.get(i).getEndTime())).getTime() > (df.parse(dateList.get(j).getStartTime())).getTime()) {
                            //有交叉 部分  不能添加
                            log.info("Conflict in time period-->saveOrUpdateInterviewSetting entity.{}", paperInterviewSetting.toString());
                            return ResultBean.failure("时间段存在冲突,请检查时间后再重试");
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                        return ResultBean.failure("时间格式出现异常,请检查时间格式并稍后再试");
                    }
                }
            }

        }
        //编辑
        if (list.size()>0) {
            if (paperInterviewSetting.getId() != list.get(0).getId()) {
                //该分组的预约时间段 已经存在 不能重复添加
                return ResultBean.failure("该分组的 预约时间段已经存在 ,不能重复编辑");
            }
        }
        boolean b = paperInterviewSettingService.saveOrUpdate(paperInterviewSetting);

        return new ResultBean(200, null, b, null);

    }

    @ApiOperation("删除 预约时间")
    @PostMapping(value = "/delete/interviewSetting")
    public ResultBean deleteInterviewSettingList(@RequestBody PaperInterviewSetting paperInterviewSetting, @RequestHeader Long accountId) {
        //如果已经被绑定 就不能删除
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("interview_id", paperInterviewSetting.getId());
        List list = paperInterviewUserRelationService.list(wrapper);
        if (list.size() > 0) {
            return ResultBean.failure("当前时间段已被预约，不能删除");
        }

        //删除预约时间段
        boolean b = paperInterviewSettingService.deleteInterviewSetting(paperInterviewSetting);
        return new ResultBean(200, null, true, null);
    }

    @ApiOperation("预约时间设置 分页列表")
    @PostMapping(value = "/query/interviewSettingList")
    public ResultBean queryInterviewSettingList(@RequestBody Map<String, Integer> map, @RequestHeader Long accountId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("paper_group_id",map.get("id"));
        Page<PaperInterviewSetting> page = paperInterviewSettingService.page(new Page<>(map.get("jumpPage"), map.get("pageSize")),wrapper);

        return new ResultBean(200, null, true, page);
    }

    @ApiOperation("上传论文")
    @PostMapping(value = "/save/paperUpload")
    public ResultBean savePaperUpload(@RequestBody PaperUploadRecord paperUploadRecord, @RequestHeader Long accountId) {
        log.info("paper save paperUploadRecord entity.{}", paperUploadRecord);
        //论文分组不能为空
        if (ObjectUtils.isEmpty(paperUploadRecord.getPaperGroupId())) {
            return ResultBean.failure("论文分组不能为空");
        }

        paperUploadRecord.setReviewStatus(SignDec.paperReviewStatus.REVIEW_PENDING);

        boolean b = paperUploadRecordService.save(paperUploadRecord);

        return new ResultBean(200, null, b, null);
    }

    @ApiOperation("查看 预约面试 页面")
    @PostMapping(value = "/query/scheduleInterview")
    public ResultBean queryScheduleInterview(@RequestBody Map<String, String> map, @RequestHeader Long accountId) {

        Long id = Long.valueOf(map.get("id"));
        if (ObjectUtils.isEmpty(id)) {
            return ResultBean.failure("论文记录不存在");
        }
        PaperScheduleInterviewDTO dto = paperInterviewUserRelationService.queryScheduleInterview(id, dataBase);

        return new ResultBean(200, null, true, dto);
    }

    @ApiOperation("预约面试保存")
    @PostMapping(value = "/save/scheduleInterview")
    public ResultBean saveScheduleInterview(@RequestBody PaperInterviewUserRelation paperInterviewUserRelation, @RequestHeader Long accountId) {
        //论文id不能为空
        if (ObjectUtils.isEmpty(paperInterviewUserRelation.getPaperId())) {
            return ResultBean.failure("论文id不能为空");
        }
        //根据 id查询详情
        PaperInterviewSetting byId = paperInterviewSettingService.getById(paperInterviewUserRelation.getInterviewId());

        if (ObjectUtils.isEmpty(byId)) {
            return ResultBean.failure("当前时间配置不存在");
        }

        //查看是否 还有 空闲时间 可预约   已预约人数!= 可预约人数
        if (byId.getCanBookedNumber() == byId.getBookedNumber()) {
            //预约 人数已满 不可预约
            return ResultBean.failure("预约人数已满，不可预约");
        }

        paperInterviewUserRelation.setBookedDate(byId.getBookedDate());
        paperInterviewUserRelation.setStartTime(byId.getStartTime());
        paperInterviewUserRelation.setEndTime(byId.getEndTime());


        boolean b = paperInterviewUserRelationService.save(paperInterviewUserRelation);
        boolean result = false;
        if (b) {
            //改变预约配置表  可预约人数+1
            //先查询
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("id", paperInterviewUserRelation.getInterviewId());
            List<PaperInterviewSetting> list = paperInterviewSettingService.list(wrapper);

            if (list.size() > 0) {
                //预约人数 +1  更新 PaperInterviewSetting 预约时间配置表
                PaperInterviewSetting paperInterviewSetting = list.get(0);
                if (ObjectUtils.isEmpty(paperInterviewSetting.getBookedNumber())) {
                    paperInterviewSetting.setBookedNumber(0);
                }
                paperInterviewSetting.setBookedNumber(paperInterviewSetting.getBookedNumber() + 1);
                result = paperInterviewSettingService.updateById(paperInterviewSetting);
            }
        }
        return new ResultBean(200, null, result, null);
    }

    @ApiOperation("评分")
    @PostMapping(value = "/save/interviewScore")
    public ResultBean saveInterviewScore(@RequestBody PaperInterviewScore paperInterviewScore, @RequestHeader Long accountId) {
        //论文id不能为空
        log.info(" paper-setting saveInterviewScore entity.{}", paperInterviewScore);
        if (ObjectUtils.isEmpty(paperInterviewScore.getPaperId())) {
            return ResultBean.failure("论文id不能为空");
        }
        //评分的时候判断一下 是否 最后一次评分 需要计算平均分
        boolean b = paperInterviewScoreService.save(paperInterviewScore);
        if (b) {
            //计算平均分
            boolean b1 = paperInterviewScoreService.calculateAverageScore(paperInterviewScore.getPaperId());
            if (!b1) {
                return new ResultBean(500, "评分成功,计算平均分出现异常", false, null);
            }
        }
        return new ResultBean(200, null, b, null);
    }

    @ApiOperation("面试管理 分页列表")
    @PostMapping("/query/interviewSettingPage")
    public ResultBean queryInterviewSettingPage(@RequestBody PaperInterviewSettingSearchVo paperInterviewSettingSearchVo, @RequestHeader Long accountId) {
        Page<PaperInterviewSettingPageDTO> dto = paperInterviewSettingService.queryInterviewSettingPage(new Page<>(paperInterviewSettingSearchVo.getJumpPage(), paperInterviewSettingSearchVo.getPageSize()), paperInterviewSettingSearchVo, dataBase);
        return ResultBean.success(dto, null);
    }

    @ApiOperation("设置及格分数")
    @PostMapping("/save/setPassScore")
    public ResultBean savePassScore(@RequestBody PaperScoreSetting paperScoreSetting, @RequestHeader Long accountId) {
        log.info("paperScoreSetting entity.{}", paperScoreSetting);
        if (ObjectUtils.isEmpty(paperScoreSetting.getTestId()) || ObjectUtils.isEmpty(paperScoreSetting.getSource())) {
            return ResultBean.failure("考试id或者报名来源不能为空");
        }
        boolean isExist = paperScoreSettingService.checkIsExist(paperScoreSetting);
        if (!isExist) {
            return ResultBean.failure("当前考试及格分数已设置，请勿重复提交");
        }
        boolean b = paperScoreSettingService.save(paperScoreSetting);
        return new ResultBean(200, null, b, null);
    }

    @ApiOperation("编辑及格分数")
    @PostMapping("/save/updatePassScore")
    public ResultBean updatePassScore(@RequestBody PaperScoreSetting paperScoreSetting, @RequestHeader Long accountId) {
        log.info("paperScoreSetting entity.{}", paperScoreSetting);
        if (ObjectUtils.isEmpty(paperScoreSetting.getId()) || ObjectUtils.isEmpty(paperScoreSetting.getScore())) {
            return ResultBean.failure("id或者分数不能为空");
        }
        boolean b = paperScoreSettingService.updateById(paperScoreSetting);
        return new ResultBean(200, null, b, null);
    }

    @ApiOperation("批量导出 面试管理分页列表")
    @PostMapping(value = "/export/interviewSettingList")
    public void exportInterviewSettingList(HttpServletResponse response, @RequestParam String ids) {

        String fileName = "面试管理列表";

        // 自定义Excel文件名
        String excelName = fileName;

        List<PaperInterviewSettingExportDTO> dto = paperInterviewSettingService.exportInterviewSettingList(ids);

        // eazyexcel工具类实现Excel文件导出
        ExcelUtil.writeExcel(response, dto, fileName, excelName, new PaperInterviewSettingExportDTO());

    }

    @ApiOperation("面试管理详情")
    @PostMapping(value = "/query/interviewSettingDetail")
    public ResultBean queryInterviewSettingDetail(HttpServletResponse response, @RequestBody Map<String, String> map) {
        Long id = Long.valueOf(map.get("id"));
        PaperInterviewSettingPageDTO dto = paperInterviewSettingService.queryInterviewSettingDetail(id);

        return ResultBean.success(dto, null);

    }


    @ApiOperation("查询专业版/简易版报名列表")
    @PostMapping(value = "/query/queryTestList")
    public ResultBean queryProfessionaplSignUpList(HttpServletResponse response, @RequestBody Map<String, String> map) {
        Integer source = Integer.valueOf(map.get("source"));
        List<PaperApplyDTO> applyDTOList = new ArrayList<>();
        if (source == 0) {
            applyDTOList = paperUploadRecordService.getProfessionalApplyList();
        } else {
            applyDTOList = paperUploadRecordService.getSimpleApplyList();
        }
        return ResultBean.success(applyDTOList, null);

    }


    @ApiOperation("及格分数分页列表")
    @PostMapping(value = "/query/queryPassScorePage")
    public ResultBean queryPassScorePage(HttpServletResponse response, @RequestBody PaperScoreSettingPageDTO paperScoreSettingPageDTO) {
        Page<PaperScoreSettingPageDTO> pageBean = paperScoreSettingService.queryPassScorePage(new Page<>(paperScoreSettingPageDTO.getJumpPage(), paperScoreSettingPageDTO.getPageSize()), paperScoreSettingPageDTO, dataBase);
        return ResultBean.success(pageBean, null);

    }


    @ApiOperation("预约时间详情")
    @PostMapping(value = "/query/queryReservationTimeDetail")
    public ResultBean queryReservationTimeDetail(HttpServletResponse response, @RequestBody Map<String,String> map) {
       Long id = Long.valueOf(map.get("id"));
        PaperInterviewSetting bean  = paperInterviewSettingService.getById(id);
        return ResultBean.success(bean, null);

    }

}

