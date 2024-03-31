package com.misssyc.seed.admin.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.misssyc.seed.common.core.enums.OperateType;
import com.misssyc.seed.common.core.web.Result;
import com.misssyc.seed.common.core.web.ResultHelper;
import com.misssyc.seed.common.log.annotation.Log;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.misssyc.seed.admin.pojo.vo.UserVO;
import com.misssyc.seed.admin.pojo.vo.UserQueryVO;
import com.misssyc.seed.admin.pojo.vo.UserAddOrUpdateVO;
import com.misssyc.seed.admin.service.UserService;
import com.misssyc.seed.common.ds.vo.PageVO;
import com.misssyc.seed.common.ds.vo.PageQueryVO;
import java.lang.Integer;

/**
 * 用户信息Controller
 *
 * @author 李升平
 * @since  2024-03-25 23:30:54
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户信息
     *
     * @param userId 用户信息主键
     * @return 用户信息
     */
    @ApiOperation("查询用户信息")
    @GetMapping("/{userId}")
    @SaCheckPermission("seed:user:detail")
    public Result<UserVO> selectUserById(@PathVariable Long userId) {
        return ResultHelper.ok(userService.selectUserById(userId));
    }

    /**
     * 查询用户信息列表
     *
     * @param user 用户信息
     * @return 用户信息集合
     */
    @ApiOperation("查询用户信息列表")
    @PostMapping("/list")
    @SaCheckPermission("seed:user:list")
    public Result<PageVO<UserVO>> selectUserList(@RequestBody PageQueryVO<UserQueryVO> user) {
        return ResultHelper.ok(userService.selectUserList(user));
    }

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     */
    @ApiOperation("新增用户信息")
    @PostMapping
    @SaCheckPermission("seed:user:insert")
    @Log(title = "新增用户信息", operateType = OperateType.INSERT)
    public Result<Long> insertUser(@RequestBody UserAddOrUpdateVO user) {
        return ResultHelper.ok(userService.insertUser(user));
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     */
    @ApiOperation("修改用户信息")
    @PutMapping
    @SaCheckPermission("seed:user:update")
    @Log(title = "修改用户信息", operateType = OperateType.UPDATE)
    public Result<Void> updateUser(@RequestBody UserAddOrUpdateVO user) {
        userService.updateUser(user);
        return ResultHelper.ok();
    }

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户信息主键集合
     * @return 结果
     */
    @ApiOperation("批量删除用户信息")
    @DeleteMapping
    @SaCheckPermission("seed:user:delete")
    @Log(title = "批量删除用户信息", operateType = OperateType.DELETE)
    public Result<Integer> deleteUserByIds(@RequestBody List<Long> userIds) {
        return ResultHelper.ok(userService.deleteUserByIds(userIds));
    }
}