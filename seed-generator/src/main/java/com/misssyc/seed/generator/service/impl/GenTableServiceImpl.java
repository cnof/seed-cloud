package com.misssyc.seed.generator.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.misssyc.seed.common.core.exception.SeedRuntimeException;
import com.misssyc.seed.common.core.utils.StringUtils;
import com.misssyc.seed.generator.dao.GenTableColumnMapper;
import com.misssyc.seed.generator.dao.GenTableMapper;
import com.misssyc.seed.generator.po.GenTable;
import com.misssyc.seed.generator.po.GenTableColumn;
import com.misssyc.seed.generator.pojo.dto.GenTableAddDTO;
import com.misssyc.seed.generator.service.GenTableService;
import com.misssyc.seed.generator.utils.GenUtils;
import com.misssyc.seed.generator.utils.VelocityInitializer;
import com.misssyc.seed.generator.utils.VelocityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Objects;

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

    }

    @Override
    public void generateCode(Long tableId) {

    }


}
