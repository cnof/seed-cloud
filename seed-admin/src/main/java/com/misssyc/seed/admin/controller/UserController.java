package com.misssyc.seed.admin.controller;

import com.misssyc.seed.common.core.web.Result;
import com.misssyc.seed.common.core.web.ResultHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 33992
 * @since 2024/2/22
 **/
@Api(tags = "用户管理")
@RestController
@RequestMapping("/users")
public class UserController {

    @ApiOperation("查询用户信息")
    @GetMapping
    public Result<Void> list() {
        return ResultHelper.ok();
    }
}
