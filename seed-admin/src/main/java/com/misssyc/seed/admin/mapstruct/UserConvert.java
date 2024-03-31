package com.misssyc.seed.admin.mapstruct;

import com.misssyc.seed.admin.po.User;
import com.misssyc.seed.admin.pojo.vo.UserVO;
import com.misssyc.seed.admin.pojo.vo.UserAddOrUpdateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * UserConvert 数组类型转换处理
 *
 * @author 李升平
 * @since  2024-03-25 23:30:54
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserVO convert(User po);

    User convert(UserAddOrUpdateVO vo);

    List<UserVO> convert(List<User> pos);
}