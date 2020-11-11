package com.cb.signupstage.common.enums;

/**
 * @author wwn
 * @create_time 2020/9/1
 * @description
 */
public enum DBStatusEnum {
    /**
     * 数据库状态,生效
     */
    EFFECT_STATUS(1,"生效状态"),

    INVALID_STATUS(0,"失效状态"),

    ARCHIVE_STATUS(2, "归档状态");

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 状态
     */
    private Integer code;

    /**
     * 详情
     */
    private String msg;

    DBStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
