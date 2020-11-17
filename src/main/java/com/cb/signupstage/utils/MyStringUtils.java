package com.cb.signupstage.utils;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wwn
 * @create_time 2020/9/2
 * @description
 */
public  class MyStringUtils {


    public static List<String> getListFormSplit(String source,String split){
        if (StringUtils.isEmpty(source)){
            return Collections.EMPTY_LIST;
        }
        String[] splitArr = source.split(split);
        List<String> list = new ArrayList<>(splitArr.length);
        for (String str : splitArr){
            list.add(str);
        }
        return list;
    }
}
