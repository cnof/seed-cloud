package com.misssyc.seed.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.misssyc.seed.common.core.exception.SeedRuntimeException;
import com.misssyc.seed.generator.dao.GenTableColumnMapper;
import com.misssyc.seed.generator.dao.GenTableMapper;
import com.misssyc.seed.generator.po.GenTable;
import com.misssyc.seed.generator.po.GenTableColumn;
import com.misssyc.seed.generator.pojo.dto.GenTableAddDTO;
import com.misssyc.seed.generator.service.GenTableService;
import com.misssyc.seed.generator.utils.GenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 代码生成业务表 服务实现类
 * </p>
 *
 * @author 
 * @since 2024-03-01
 */
@Slf4j
@Service
public class GenTableServiceImpl extends ServiceImpl<GenTableMapper, GenTable>
        implements GenTableService {

    @Autowired
    private GenTableColumnMapper genTableColumnMapper;

    @Override
    public List<GenTable> selectDbTableListByNames(List<String> tableNames) {
        return baseMapper.selectDbTableListByNames(tableNames);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importGenTable(List<GenTable> tableList, GenTableAddDTO param) {
        try {
            for (GenTable table : tableList) {
                String tableName = table.getTableName();
                GenUtils.initTable(table, "", param);
                int row = baseMapper.insert(table);
                if (row > 0) {
                    // 保存列信息
                    List<GenTableColumn> genTableColumns = genTableColumnMapper.selectDbTableColumnsByName(tableName);
                    for (GenTableColumn column : genTableColumns)
                    {
                        GenUtils.initColumnField(column, table);
                        genTableColumnMapper.insert(column);
                    }
                }
            }
        } catch (Exception err) {
            throw new SeedRuntimeException("错误：导入失败：" + err.getMessage());
        }
    }
}
