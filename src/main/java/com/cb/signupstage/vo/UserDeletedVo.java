package com.cb.signupstage.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: ly
 * @time: 2020/12/18 10:44
 * @description:用户删除
 */
@Data
public class UserDeletedVo {
    private Long id;
    private List<Long> groupIds;
}
