package com.misssyc.seed.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.misssyc.seed.common.ds.vo.PageQueryVO;
import com.misssyc.seed.common.ds.vo.PageVO;
import com.misssyc.seed.generator.dao.GenTableColumnMapper;
import com.misssyc.seed.generator.mapstruct.GenTableColumnConvert;
import com.misssyc.seed.generator.po.GenTableColumn;
import com.misssyc.seed.generator.pojo.vo.GenTableColumnAddOrUpdateVO;
import com.misssyc.seed.generator.pojo.vo.GenTableColumnQueryVO;
import com.misssyc.seed.generator.pojo.vo.GenTableColumnVO;
import com.misssyc.seed.generator.service.GenTableColumnService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 服务实现类
 * </p>
 *
 * @author 
 * @since 2024-03-01
 */
@Service
public class GenTableColumnServiceImpl extends ServiceImpl<GenTableColumnMapper, GenTableColumn>
        implements GenTableColumnService {

    @Override
    public GenTableColumnVO selectGenTableColumnById(Long id) {
        GenTableColumn po = getById(id);
        return GenTableColumnConvert.INSTANCE.convert(po);
    }

    @Override
    public PageVO<GenTableColumnVO> selectGenTableColumnList(PageQueryVO<GenTableColumnQueryVO> param) {
        Page<GenTableColumn> page = new Page<>(param.getCurrentPage(), param.getPageSize());
        GenTableColumnQueryVO data = param.getData();
        QueryWrapper<GenTableColumn> queryWrapper = new QueryWrapper<>();
        if (null != data) {
            if (data.getColumnId() != null) {
                queryWrapper.lambda().eq(GenTableColumn::getColumnId, data.getColumnId());
            }
            if (data.getColumnName() != null) {
                queryWrapper.lambda().like(GenTableColumn::getColumnName, data.getColumnName());
            }
        }
        Page<GenTableColumn> pageInfo = page(page, queryWrapper);
        List<GenTableColumnVO> vos = GenTableColumnConvert.INSTANCE.convert(pageInfo.getRecords());
        return new PageVO<>(vos, pageInfo);
    }

    @Override
    public Long insert(GenTableColumnAddOrUpdateVO param) {
        GenTableColumn po = GenTableColumnConvert.INSTANCE.convert(param);
        save(po);
        return po.getColumnId();
    }

    @Override
    public void update(GenTableColumnAddOrUpdateVO param) {
        GenTableColumn po = GenTableColumnConvert.INSTANCE.convert(param);
        updateById(po);
    }

    @Override
    @Transactional
    public int deleteByIds(List<Long> ids) {
        return baseMapper.deleteBatchIds(ids);
    }
}
