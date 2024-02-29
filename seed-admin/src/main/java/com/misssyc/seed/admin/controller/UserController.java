package com.misssyc.seed.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.misssyc.seed.common.core.web.Result;
import com.misssyc.seed.common.core.web.ResultHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author 李生平
 * @since 2024/2/22
 **/
@Api(tags = "用户管理")
@RestController
@RequestMapping("/users")
public class UserController {

    @ApiOperation("查询用户信息")
    @GetMapping
    public Result<Object> list() {
        return ResultHelper.ok(StpUtil.getLoginId());
    }

    @ApiOperation("新增用户信息")
    @PostMapping
    public Result<Void> add() {
        return ResultHelper.ok();
    }

    @ApiOperation("更新用户信息")
    @PutMapping
    public Result<Void> update() {
        return ResultHelper.ok();
    }

    @ApiOperation("删除用户信息")
    @DeleteMapping
    public Result<Void> delete() {
        return ResultHelper.ok();
    }
}
