package com.cb.signupstage.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cb.signupstage.dto.InterviewListDTO;
import com.cb.signupstage.dto.PaperScheduleInterviewDTO;
import com.cb.signupstage.entity.PaperInterviewSetting;
import com.cb.signupstage.entity.PaperInterviewUserRelation;
import com.cb.signupstage.mapper.PaperInterviewSettingMapper;
import com.cb.signupstage.mapper.PaperInterviewUserRelationMapper;
import com.cb.signupstage.mapper.PaperUploadRecordMapper;
import com.cb.signupstage.service.PaperInterviewUserRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 论文面试预约详情表 服务实现类
 * </p>
 *
 * @author ly
 * @since 2020-12-28
 */
@Service
public class PaperInterviewUserRelationServiceImpl extends ServiceImpl<PaperInterviewUserRelationMapper, PaperInterviewUserRelation> implements PaperInterviewUserRelationService {


    @Autowired
    private PaperInterviewSettingMapper paperInterviewSettingMapper;
    @Autowired
    private PaperUploadRecordMapper paperUploadRecordMapper;

    @Override
    public PaperScheduleInterviewDTO queryScheduleInterview(Long id,String dataBase) {
        PaperScheduleInterviewDTO dto = new PaperScheduleInterviewDTO();
        //查询个人基本信息 TODO
        paperUploadRecordMapper.selectInfoById(id,dataBase);
        //根据论文分组id 查询可预约日期时间 List
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("paper_group_id", id);
        List<InterviewListDTO> list = paperInterviewSettingMapper.selectListGroupByBookedDate(wrapper);
        if (list.size() > 0) {
            //将 查到的 时间段 list进行分组 得到一个map 集合 但并不是我们想要的数据结构
            Map<String, List<InterviewListDTO>> map = list.stream().collect(Collectors.groupingBy(InterviewListDTO::getBookedDate, Collectors.toList()));
            //将map 组装成 list返回给前端
            List<JSONObject> bookedDateRecords = map.keySet().stream().map(key -> {
                JSONObject goodObject = new JSONObject(true);
                goodObject.put("bookedDate", key.toUpperCase());
                goodObject.put("bookedTimeList", map.get(key));
                System.out.println(goodObject);
                return goodObject;
            }).collect(Collectors.toList());

            for (JSONObject bookedDateRecord : bookedDateRecords) {

                boolean result = bookedDateRecord.getJSONArray("bookedTimeList").stream().allMatch(isFull ->  (isFull.equals("true")));
                bookedDateRecord.put("isFull",result);
            }
            dto.setTimeList(bookedDateRecords);
        }
        return dto;
    }
}
