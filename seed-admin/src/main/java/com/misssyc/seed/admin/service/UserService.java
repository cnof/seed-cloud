package com.misssyc.seed.admin.service;

import java.util.List;
import com.misssyc.seed.admin.po.User;
import com.misssyc.seed.admin.pojo.vo.UserVO;
import com.misssyc.seed.admin.pojo.vo.UserQueryVO;
import com.misssyc.seed.admin.pojo.vo.UserAddOrUpdateVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.misssyc.seed.common.ds.vo.PageVO;
import com.misssyc.seed.common.ds.vo.PageQueryVO;

/**
 * 用户信息Service接口
 *
 * @author 李升平
 * @since  2024-03-25 23:30:54
 */
public interface UserService extends IService<User> {

    /**
     * 查询用户信息
     *
     * @param userId 用户信息主键
     * @return 用户信息
     */
    UserVO selectUserById(Long userId);

    /**
     * 查询用户信息列表
     *
     * @param user 用户信息
     * @return 用户信息集合
     */
    PageVO<UserVO> selectUserList(PageQueryVO<UserQueryVO> user);

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     */
     Long insertUser(UserAddOrUpdateVO user);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     */
    void updateUser(UserAddOrUpdateVO user);

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户信息主键集合
     * @return 结果
     */
    int deleteUserByIds(List<Long> userIds);
}