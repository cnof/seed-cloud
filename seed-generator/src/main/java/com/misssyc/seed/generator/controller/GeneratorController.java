package com.misssyc.seed.generator.controller;

import com.misssyc.seed.common.core.enums.OperateType;
import com.misssyc.seed.common.core.web.Result;
import com.misssyc.seed.common.core.web.ResultHelper;
import com.misssyc.seed.common.log.annotation.Log;
import com.misssyc.seed.generator.po.GenTable;
import com.misssyc.seed.generator.pojo.vo.GenTableQueryVO;
import com.misssyc.seed.generator.pojo.vo.GenTableVO;
import com.misssyc.seed.generator.service.GenTableService;
import com.misssyc.seed.generator.service.GeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李生平
 * @since 2024/2/27
 **/
@Api(tags = "后台管理-代码生成")
@RestController
@RequestMapping("/generator")
@RequiredArgsConstructor
public class GeneratorController {

    private final GeneratorService generatorService;

    private final GenTableService genTableService;

    @ApiOperation("根据表名称生成表字段信息")
    @Log(title = "根据表名称生成表字段信息", operateType = OperateType.IMPORT)
    @PostMapping("/import-table")
    public Result<Void> importTableSave(@RequestBody List<String> tableNames) {
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        generatorService.importGenTable(tableList);
        return ResultHelper.ok();
    }

    @ApiOperation("生成代码")
    @Log(title = "生成代码", operateType = OperateType.INSERT)
    @PostMapping("/generate-code")
    public Result<Void> generateCode(Long tableId) {
        generatorService.generateCode(tableId);
        return ResultHelper.ok();
    }

    @ApiOperation("查询未被生成代码的表信息")
    @GetMapping("/unused/tables")
    public Result<List<GenTableVO>> queryToBeGenTables(GenTableQueryVO param) {
        return ResultHelper.ok(generatorService.queryToBeGenTables(param));
    }
}
