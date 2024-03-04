package com.misssyc.seed.generator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.misssyc.seed.generator.po.GenTableColumn;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2024-03-01
 */
@Repository
public interface GenTableColumnMapper extends BaseMapper<GenTableColumn> {

    /**
     * 根据表名称查询列信息
     *
     * @param tableName 表名称
     * @return 列信息
     */
    List<GenTableColumn> selectDbTableColumnsByName(String tableName);
}
