package com.misssyc.seed.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.misssyc.seed.common.ds.vo.PageQueryVO;
import com.misssyc.seed.common.ds.vo.PageVO;
import com.misssyc.seed.generator.po.GenTable;
import com.misssyc.seed.generator.pojo.vo.GenTableAddOrUpdateVO;
import com.misssyc.seed.generator.pojo.vo.GenTableQueryVO;
import com.misssyc.seed.generator.pojo.vo.GenTableVO;

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

    GenTableVO selectGenTableById(Long id);

    PageVO<GenTableVO> selectGenTableList(PageQueryVO<GenTableQueryVO> param);

    Long insert(GenTableAddOrUpdateVO param);

    void update(GenTableAddOrUpdateVO param);

    int deleteByIds(List<Long> ids);
}
