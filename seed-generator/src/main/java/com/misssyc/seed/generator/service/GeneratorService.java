package com.misssyc.seed.generator.service;

import com.misssyc.seed.generator.po.GenTable;
import com.misssyc.seed.generator.pojo.vo.GenTableQueryVO;
import com.misssyc.seed.generator.pojo.vo.GenTableVO;

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
     */
    void importGenTable(List<GenTable> tableList);

    void generateCode(Long tableId);

    /**
     * 查询未被生成过代码的表信息
     */
    List<GenTableVO> queryToBeGenTables(GenTableQueryVO param);
}
