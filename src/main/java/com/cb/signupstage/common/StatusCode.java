package com.cb.signupstage.common;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class StatusCode {

    public final static Integer SUCCESS_CODE = 200;

    public final static Integer SYSTEM_EXCEPTION_CODE = 500;

    public final static Integer MISSING_PARAMETERS_CODE = 000;

    public final static Integer CREATE_EXIST_DATA_CODE = 001;

    public final static Integer DELETE_NOT_EXIST_DATA_CODE = 002;

    public final static Integer QUERY_NOT_EXIST_DATA_CODE = 003;

    public final static Integer UPDATE_DATA_FAIL_CODE = 004;

}

