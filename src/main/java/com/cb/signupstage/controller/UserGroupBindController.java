package com.cb.signupstage.controller;


import com.cb.signupstage.common.ResultBean;
import com.cb.signupstage.service.UserGroupBindService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 用户分组关系表 前端控制器
 * </p>
 *
 * @author ly
 * @since 2020-11-13
 */
@Api(tags = "用户分组关系表")
@RestController
@RequestMapping("/user-group-bind")
public class UserGroupBindController {

    @Autowired
    private UserGroupBindService userGroupBindService;




}
