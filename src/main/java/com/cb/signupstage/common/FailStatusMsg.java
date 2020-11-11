package com.cb.signupstage.common;

/**
 * @author: ly
 * @time: 2020/11/10 17:50
 * @description: 错误返回状态内容
 */
public class FailStatusMsg {
    public final static String  MISSING_PARAMETERS = "缺失参数";

    public final static String  PARSE_JSON_FAIL = "解析JSON失败";

    public final static String  SYSTEM_EXCEPTION = "系统异常";

    public final static String  UPDATE_DATA_FAIL = "更新数据失败";

    public final static String  CREATE_EXIST_DATA = "创建的数据已经存在";

    public final static String  DELETE_NOT_EXIST_DATA = "删除的数据不存在";

    public final static String QUERY_NOT_EXIST_DATA = "查询的数据不存在";

    public final static String PARAMETERS_WRONG_FORMAT = "参数格式错误";
}
