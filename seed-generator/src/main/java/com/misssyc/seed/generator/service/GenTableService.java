package com.misssyc.seed.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.misssyc.seed.generator.po.GenTable;
import com.misssyc.seed.generator.pojo.dto.GenTableAddDTO;

import java.util.List;

/**
 * <p>
 * 代码生成业务表 服务类
 * </p>
 *
 * @author 
 * @since 2024-03-01
 */
public interface GenTableService extends IService<GenTable> {

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableListByNames(List<String> tableNames);

    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     * @param param
     */
    void importGenTable(List<GenTable> tableList, GenTableAddDTO param);
}
