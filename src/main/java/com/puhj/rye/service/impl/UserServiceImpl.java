package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.bo.PasswordBO;
import com.puhj.rye.dto.UserPageDTO;
import com.puhj.rye.entity.User;
import com.puhj.rye.mapper.UserMapper;
import com.puhj.rye.service.UserService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.UserListVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getByUsername(String username) {
        return this.userMapper.selectByUsername(username);
    }

    @Override
    public PageVO<UserListVO> getPageList(Page<UserListVO> page, UserPageDTO userPageDTO) {
        Page<UserListVO> pageList = this.userMapper.selectPageList(page, userPageDTO);
        return new PageVO<>(pageList);
    }

    @Override
    public int updatePassword(PasswordBO passwordBO) {
        User currUser = this.userMapper.selectById(passwordBO.getUserId());
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", passwordBO.getUserId());
        updateWrapper.set("password", passwordBO.getNewPassword());
        return this.userMapper.update(currUser, updateWrapper);
    }

}
