package com.misssyc.seed.admin.service.impl;

import org.springframework.stereotype.Service;
import com.misssyc.seed.admin.dao.UserMapper;
import com.misssyc.seed.admin.po.User;
import com.misssyc.seed.admin.pojo.vo.UserVO;
import com.misssyc.seed.admin.pojo.vo.UserQueryVO;
import com.misssyc.seed.admin.pojo.vo.UserAddOrUpdateVO;
import com.misssyc.seed.admin.service.UserService;
import com.misssyc.seed.admin.mapstruct.UserConvert;
import com.misssyc.seed.common.ds.vo.PageVO;
import com.misssyc.seed.common.ds.vo.PageQueryVO;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * 用户信息Service业务层处理
 *
 * @author 李升平
 * @since  2024-03-25 23:30:54
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    /**
     * 查询用户信息
     *
     * @param userId 用户信息主键
     * @return 用户信息
     */
     @Override
     public UserVO selectUserById(Long userId) {
         User po = getById(userId);
         return UserConvert.INSTANCE.convert(po);
     }

    /**
     * 查询用户信息列表
     *
     * @param user 用户信息
     * @return 用户信息集合
     */
    @Override
    public PageVO<UserVO> selectUserList(PageQueryVO<UserQueryVO> user) {
        UserQueryVO data = user.getData();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (null != data) {
            if (data.getDeptId() != null) {
                queryWrapper.lambda().eq(User::getDeptId, data.getDeptId());
            }
            if (data.getUserName() != null) {
                queryWrapper.lambda().like(User::getUserName, data.getUserName());
            }
            if (data.getFullName() != null) {
                queryWrapper.lambda().like(User::getFullName, data.getFullName());
            }
            if (data.getNickName() != null) {
                queryWrapper.lambda().like(User::getNickName, data.getNickName());
            }
            if (data.getUserType() != null) {
                queryWrapper.lambda().eq(User::getUserType, data.getUserType());
            }
            if (data.getEmail() != null) {
                queryWrapper.lambda().eq(User::getEmail, data.getEmail());
            }
            if (data.getPhoneNumber() != null) {
                queryWrapper.lambda().eq(User::getPhoneNumber, data.getPhoneNumber());
            }
            if (data.getSex() != null) {
                queryWrapper.lambda().eq(User::getSex, data.getSex());
            }
            if (data.getAvatar() != null) {
                queryWrapper.lambda().eq(User::getAvatar, data.getAvatar());
            }
            if (data.getPassword() != null) {
                queryWrapper.lambda().eq(User::getPassword, data.getPassword());
            }
            if (data.getStatus() != null) {
                queryWrapper.lambda().eq(User::getStatus, data.getStatus());
            }
            if (data.getLoginIp() != null) {
                queryWrapper.lambda().eq(User::getLoginIp, data.getLoginIp());
            }
            if (data.getLoginDate() != null) {
                queryWrapper.lambda().eq(User::getLoginDate, data.getLoginDate());
            }
        }
        Page<User> page = new Page<>(user.getCurrentPage(), user.getPageSize());
        Page<User> pageInfo = page(page, queryWrapper);
        List<UserVO> vos = UserConvert.INSTANCE.convert(pageInfo.getRecords());
        return new PageVO<>(vos, pageInfo);
    }

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     */
     @Override
     public Long insertUser(UserAddOrUpdateVO user) {
         User po = UserConvert.INSTANCE.convert(user);
         save(po);
         return po.getUserId();
     }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     */
    @Override
    public void updateUser(UserAddOrUpdateVO user) {
        User po =UserConvert.INSTANCE.convert(user);
        updateById(po);
    }

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户信息主键集合
     * @return 结果
     */
    @Override
    public int deleteUserByIds(List<Long> userIds) {
        return baseMapper.deleteBatchIds(userIds);
    }
}