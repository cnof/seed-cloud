package com.misssyc.seed.generator.service;

import com.misssyc.seed.generator.po.GenTable;
import com.misssyc.seed.generator.pojo.dto.GenTableAddDTO;

import java.util.List;

/**
 * @author 33992
 * @since 2024/3/5
 **/
public interface GeneratorService {

    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     * @param param 表信息
     */
    void importGenTable(List<GenTable> tableList, GenTableAddDTO param);

    void generateCode(Long tableId);
}
