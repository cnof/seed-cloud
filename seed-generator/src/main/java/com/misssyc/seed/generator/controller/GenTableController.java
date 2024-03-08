package com.misssyc.seed.generator.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.misssyc.seed.common.core.enums.OperateType;
import com.misssyc.seed.common.ds.vo.PageQueryVO;
import com.misssyc.seed.common.ds.vo.PageVO;
import com.misssyc.seed.common.core.web.Result;
import com.misssyc.seed.common.core.web.ResultHelper;
import com.misssyc.seed.common.log.annotation.Log;
import com.misssyc.seed.generator.pojo.vo.GenTableAddOrUpdateVO;
import com.misssyc.seed.generator.pojo.vo.GenTableQueryVO;
import com.misssyc.seed.generator.pojo.vo.GenTableVO;
import com.misssyc.seed.generator.service.GenTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 33992
 * @since 2024/3/5
 **/
@Api(tags = "代码生成表信息管理")
@RestController
@RequestMapping("/gen-table")
@RequiredArgsConstructor
public class GenTableController {

    private final GenTableService genTableService;

    @ApiOperation("查询表信息")
    @GetMapping("/{tableId}")
    @SaCheckPermission("generator:gen-table:detail")
    public Result<GenTableVO> selectGenTableById(@PathVariable Long tableId) {
        return ResultHelper.ok(genTableService.selectGenTableById(tableId));
    }

    @ApiOperation("查询表信息列表")
    @PostMapping("/list")
    @SaCheckPermission("generator:gen-table:list")
    public Result<PageVO<GenTableVO>> selectGenTableList(@RequestBody PageQueryVO<GenTableQueryVO> genTable) {
        return ResultHelper.ok(genTableService.selectGenTableList(genTable));
    }

    @ApiOperation("新增表信息")
    @PostMapping
    @SaCheckPermission("generator:gen-table:insert")
    @Log(title = "新增表信息", operateType = OperateType.INSERT)
    public Result<Long> insertGenTable(@RequestBody GenTableAddOrUpdateVO genTable) {
        return ResultHelper.ok(genTableService.insert(genTable));
    }

    @ApiOperation("修改表信息")
    @PutMapping
    @SaCheckPermission("generator:gen-table:update")
    @Log(title = "修改表信息", operateType = OperateType.UPDATE)
    public Result<Void> updateGenTable(@RequestBody GenTableAddOrUpdateVO genTable) {
        genTableService.update(genTable);
        return ResultHelper.ok();
    }

    @ApiOperation("批量删除表信息")
    @DeleteMapping
    @SaCheckPermission("generator:gen-table:delete")
    @Log(title = "批量删除表信息", operateType = OperateType.DELETE)
    public Result<Integer> deleteGenTableByIds(@RequestBody List<Long> ids) {
        return ResultHelper.ok(genTableService.deleteByIds(ids));
    }
}
