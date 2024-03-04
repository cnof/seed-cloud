package com.misssyc.seed.generator.mapstruct;

import com.misssyc.seed.generator.po.GenTable;
import com.misssyc.seed.generator.pojo.vo.GenTableVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 33992
 * @since 2024/3/4
 **/
@Mapper
public interface GenTableConvert {

    GenTableConvert INSTANCE = Mappers.getMapper(GenTableConvert.class);

    GenTableVO convert(GenTable po);
}
