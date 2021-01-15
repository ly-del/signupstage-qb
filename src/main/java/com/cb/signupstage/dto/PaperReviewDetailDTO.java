package com.cb.signupstage.dto;

import com.cb.signupstage.entity.PaperReview;
import com.cb.signupstage.entity.PaperUploadRecord;
import lombok.Data;

import java.util.List;

/**
 * @author: ly
 * @time: 2020/12/25 15:21
 * @description:
 */
@Data
public class PaperReviewDetailDTO extends PaperUploadRecord {

    private String userName;
    private String testName;
    private String paperGroupName;

    private List<PaperReview>  reviewList;


}
