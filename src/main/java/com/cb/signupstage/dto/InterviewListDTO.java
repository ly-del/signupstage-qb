package com.cb.signupstage.dto;

import lombok.Data;

/**
 * @author: ly
 * @time: 2020/12/28 15:03
 * @description:  预约时间段 时间集合
 */
@Data
public class InterviewListDTO {
    private Long id;
    private String bookedDate;
    private String startTime;

    /**
     * 是否已约满
     */
    private Boolean isFull;

    private String bookedNumber;

    private String canBookedNumber;
}
