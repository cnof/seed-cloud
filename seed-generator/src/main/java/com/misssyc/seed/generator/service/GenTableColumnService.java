package com.misssyc.seed.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.misssyc.seed.common.ds.vo.PageQueryVO;
import com.misssyc.seed.common.ds.vo.PageVO;
import com.misssyc.seed.generator.po.GenTableColumn;
import com.misssyc.seed.generator.pojo.vo.GenTableColumnAddOrUpdateVO;
import com.misssyc.seed.generator.pojo.vo.GenTableColumnQueryVO;
import com.misssyc.seed.generator.pojo.vo.GenTableColumnVO;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 服务类
 * </p>
 *
 * @author 
 * @since 2024-03-01
 */
public interface GenTableColumnService extends IService<GenTableColumn> {

    GenTableColumnVO selectGenTableColumnById(Long id);

    PageVO<GenTableColumnVO> selectGenTableColumnList(PageQueryVO<GenTableColumnQueryVO> param);

    Long insert(GenTableColumnAddOrUpdateVO param);

    void update(GenTableColumnAddOrUpdateVO param);

    int deleteByIds(List<Long> ids);
}
