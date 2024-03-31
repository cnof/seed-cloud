package com.misssyc.seed.admin.dao;

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.misssyc.seed.admin.po.User;

/**
 * 用户信息Mapper接口
 *
 * @author 李升平
 * @since 2024-03-25 23:30:54
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}