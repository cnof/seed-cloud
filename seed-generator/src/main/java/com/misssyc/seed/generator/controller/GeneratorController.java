package com.misssyc.seed.generator.controller;

import com.misssyc.seed.common.core.enums.OperateType;
import com.misssyc.seed.common.core.web.Result;
import com.misssyc.seed.common.core.web.ResultHelper;
import com.misssyc.seed.common.log.annotation.Log;
import com.misssyc.seed.generator.po.GenTable;
import com.misssyc.seed.generator.pojo.dto.GenTableAddDTO;
import com.misssyc.seed.generator.service.GenTableService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李生平
 * @since 2024/2/27
 **/
@Api(tags = "后台管理-代码生成")
@RestController
@RequestMapping
public class GeneratorController {

    @Autowired
    private GenTableService genTableService;

    @Log(title = "代码生成", operateType = OperateType.IMPORT)
    @PostMapping("/importTable")
    public Result<Void> importTableSave(@RequestBody GenTableAddDTO param)
    {
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(param.getTableNames());
        genTableService.importGenTable(tableList, param);
        return ResultHelper.ok();
    }

    @PostMapping("/generate-code")
    public Result<Void> generateCode(Long tableId) {
        genTableService.generateCode(tableId);
        return ResultHelper.ok();
    }
}
