package com.misssyc.seed.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.misssyc.seed.common.ds.vo.PageQueryVO;
import com.misssyc.seed.common.ds.vo.PageVO;
import com.misssyc.seed.generator.dao.GenTableColumnMapper;
import com.misssyc.seed.generator.dao.GenTableMapper;
import com.misssyc.seed.generator.mapstruct.GenTableConvert;
import com.misssyc.seed.generator.po.GenTable;
import com.misssyc.seed.generator.po.GenTableColumn;
import com.misssyc.seed.generator.pojo.vo.GenTableAddOrUpdateVO;
import com.misssyc.seed.generator.pojo.vo.GenTableQueryVO;
import com.misssyc.seed.generator.pojo.vo.GenTableVO;
import com.misssyc.seed.generator.service.GenTableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 代码生成业务表 服务实现类
 * </p>
 *
 * @author 李生平
 * @since  2024-03-01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GenTableServiceImpl extends ServiceImpl<GenTableMapper, GenTable>
        implements GenTableService {

    private final GenTableColumnMapper genTableColumnMapper;

    @Override
    public List<GenTable> selectDbTableListByNames(List<String> tableNames) {
        return baseMapper.selectDbTableListByNames(tableNames);
    }

    @Override
    public GenTableVO selectGenTableById(Long id) {
        GenTable po = getById(id);
        return GenTableConvert.INSTANCE.convert(po);
    }

    @Override
    public PageVO<GenTableVO> selectGenTableList(PageQueryVO<GenTableQueryVO> param) {
        Page<GenTable> page = new Page<>(param.getCurrentPage(), param.getPageSize());
        QueryWrapper<GenTable> queryWrapper = new QueryWrapper<>();
        Page<GenTable> pageInfo = super.page(page, queryWrapper);
        List<GenTableVO> vos = GenTableConvert.INSTANCE.convert(pageInfo.getRecords());
        return new PageVO<>(vos, pageInfo);
    }

    @Override
    public Long insert(GenTableAddOrUpdateVO param) {
        GenTable po = GenTableConvert.INSTANCE.convert(param);
        save(po);
        return po.getTableId();
    }

    @Override
    public void update(GenTableAddOrUpdateVO param) {
        GenTable po = GenTableConvert.INSTANCE.convert(param);
        updateById(po);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByIds(List<Long> ids) {
        genTableColumnMapper.delete(Wrappers.<GenTableColumn>lambdaQuery().in(GenTableColumn::getTableId, ids));
        return baseMapper.deleteBatchIds(ids);
    }
}
