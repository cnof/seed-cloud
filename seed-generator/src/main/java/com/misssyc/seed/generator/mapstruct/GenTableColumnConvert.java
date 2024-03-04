package com.misssyc.seed.generator.mapstruct;

import com.misssyc.seed.generator.po.GenTableColumn;
import com.misssyc.seed.generator.pojo.vo.GenTableColumnAddOrUpdateVO;
import com.misssyc.seed.generator.pojo.vo.GenTableColumnVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 33992
 * @since 2024/3/4
 **/
@Mapper
public interface GenTableColumnConvert {

    GenTableColumnConvert INSTANCE = Mappers.getMapper(GenTableColumnConvert.class);

    GenTableColumnVO convert(GenTableColumn po);

    GenTableColumn convert(GenTableColumnAddOrUpdateVO po);

    List<GenTableColumnVO> convert(List<GenTableColumn> pos);
}
