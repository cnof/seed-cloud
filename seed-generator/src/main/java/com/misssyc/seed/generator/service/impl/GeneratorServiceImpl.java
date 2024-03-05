package com.misssyc.seed.generator.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.misssyc.seed.common.core.exception.SeedRuntimeException;
import com.misssyc.seed.common.core.utils.StringUtils;
import com.misssyc.seed.generator.dao.GenTableColumnMapper;
import com.misssyc.seed.generator.dao.GenTableMapper;
import com.misssyc.seed.generator.po.GenTable;
import com.misssyc.seed.generator.po.GenTableColumn;
import com.misssyc.seed.generator.pojo.dto.GenTableAddDTO;
import com.misssyc.seed.generator.service.GeneratorService;
import com.misssyc.seed.generator.utils.GenUtils;
import com.misssyc.seed.generator.utils.VelocityInitializer;
import com.misssyc.seed.generator.utils.VelocityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Objects;

/**
 * @author 33992
 * @since 2024/3/5
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {

    private final GenTableMapper genTableMapper;

    private final GenTableColumnMapper genTableColumnMapper;

    @Override
    public void importGenTable(List<GenTable> tableList, GenTableAddDTO param) {
        try {
            for (GenTable table : tableList) {
                String tableName = table.getTableName();
                GenUtils.initTable(table, "", param);
                int row = genTableMapper.insert(table);
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

    @Override
    public void generateCode(Long tableId) {
        GenTable table = genTableMapper.selectById(tableId);
        List<GenTableColumn> columns = genTableColumnMapper.selectList(
                Wrappers.<GenTableColumn>lambdaQuery()
                        .eq(GenTableColumn::getTableId, table.getTableId())
        );
        // 设置主键列信息
        setPkColumn(table, columns);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table, columns);
        // 获取模板列表
        List<String> templates = VelocityUtils.getTemplateList(table.getTplCategory(), table.getTplWebType());

        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            try
            {
                String path = getGenPath(table, template);
                FileUtils.writeStringToFile(new File(path), sw.toString(), "UTF-8");
            }
            catch (IOException e)
            {
                throw new SeedRuntimeException("渲染模板失败，表名：" + table.getTableName());
            }
        }
    }

    /**
     * 设置主键列信息
     *
     * @param table 业务表信息
     */
    public void setPkColumn(GenTable table, List<GenTableColumn> columns) {

        for (GenTableColumn column : columns)
        {
            if (Objects.equals(column.getIsPk(), "1"))
            {
                table.setPkColumn(column);
                break;
            }
        }
        if (ObjectUtil.isNull(table.getPkColumn()))
        {
            table.setPkColumn(columns.get(0));
        }
    }

    /**
     * 获取代码生成地址
     *
     * @param table 业务表信息
     * @param template 模板文件路径
     * @return 生成地址
     */
    private static String getGenPath(GenTable table, String template)
    {
        String genPath = table.getGenPath();
        if (StringUtils.equals(genPath, "/"))
        {
            return System.getProperty("user.dir") + File.separator + "src" + File.separator + VelocityUtils.getFileName(template, table);
        }
        return genPath + File.separator + VelocityUtils.getFileName(template, table);
    }
}
