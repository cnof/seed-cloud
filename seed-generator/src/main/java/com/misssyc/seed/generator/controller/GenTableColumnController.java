package com.misssyc.seed.generator.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.misssyc.seed.common.core.enums.OperateType;
import com.misssyc.seed.common.core.web.Result;
import com.misssyc.seed.common.core.web.ResultHelper;
import com.misssyc.seed.common.ds.vo.PageQueryVO;
import com.misssyc.seed.common.ds.vo.PageVO;
import com.misssyc.seed.common.log.annotation.Log;
import com.misssyc.seed.generator.pojo.vo.*;
import com.misssyc.seed.generator.service.GenTableColumnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 33992
 * @since 2024/3/8
 **/
@Api(tags = "代码生成表信息字段管理")
@RestController
@RequestMapping("/gen-table/column")
@RequiredArgsConstructor
public class GenTableColumnController {

    private final GenTableColumnService genTableColumnService;

    @ApiOperation("查询表字段信息")
    @GetMapping("/table-id/{tableId}")
    @SaCheckPermission("generator:gen-table-column:detail")
    public Result<List<GenTableColumnVO>> selectGenTableColumnByTableId(@PathVariable Long tableId) {
        return ResultHelper.ok(genTableColumnService.selectGenTableColumnByTableId(tableId));
    }

    @ApiOperation("查询表字段信息")
    @GetMapping("/{columnId}")
    @SaCheckPermission("generator:gen-table-column:detail")
    public Result<GenTableColumnVO> selectGenTableColumnById(@PathVariable Long columnId) {
        return ResultHelper.ok(genTableColumnService.selectGenTableColumnById(columnId));
    }

    @ApiOperation("查询表字段信息列表")
    @PostMapping("/list")
    @SaCheckPermission("generator:gen-table-column:list")
    public Result<PageVO<GenTableColumnVO>> selectGenTableColumnList(@RequestBody PageQueryVO<GenTableColumnQueryVO> genTableColumn) {
        return ResultHelper.ok(genTableColumnService.selectGenTableColumnList(genTableColumn));
    }

    @ApiOperation("新增表字段信息")
    @PostMapping
    @SaCheckPermission("generator:gen-table-column:insert")
    @Log(title = "新增表字段信息", operateType = OperateType.INSERT)
    public Result<Long> insertGenTableColumn(@RequestBody GenTableColumnAddOrUpdateVO param) {
        return ResultHelper.ok(genTableColumnService.insert(param));
    }

    @ApiOperation("修改表字段信息")
    @PutMapping
    @SaCheckPermission("generator:gen-table-column:update")
    @Log(title = "修改表字段信息", operateType = OperateType.UPDATE)
    public Result<Void> updateGenTableColumn(@RequestBody GenTableColumnAddOrUpdateVO param) {
        genTableColumnService.update(param);
        return ResultHelper.ok();
    }

    @ApiOperation("批量删除表字段信息")
    @DeleteMapping
    @SaCheckPermission("generator:gen-table-column:delete")
    @Log(title = "批量删除表字段信息", operateType = OperateType.DELETE)
    public Result<Integer> deleteGenTableColumnByIds(@RequestBody List<Long> ids) {
        return ResultHelper.ok(genTableColumnService.deleteByIds(ids));
    }
}
