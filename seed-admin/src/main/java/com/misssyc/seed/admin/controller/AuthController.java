package com.misssyc.seed.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.misssyc.seed.common.core.web.Result;
import com.misssyc.seed.common.core.web.ResultHelper;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 33992
 * @since 2024/2/22
 **/
@Api(tags = "后台管理-登录")
@RestController
@RequestMapping
public class AuthController {

    @PostMapping("/login")
    public Result<String> login() {
        StpUtil.login("login");
        return ResultHelper.ok();
    }

    @PutMapping("/logout")
    public Result<String> logout() {
        StpUtil.logout();
        return ResultHelper.ok();
    }
}
