package com.misssyc.seed.generator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.misssyc.seed.generator.po.GenTable;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 代码生成业务表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2024-03-01
 */
@Repository
public interface GenTableMapper extends BaseMapper<GenTable> {

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableListByNames(@Param("tableNames") List<String> tableNames);
}
