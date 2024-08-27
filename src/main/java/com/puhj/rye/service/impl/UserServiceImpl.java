package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.bo.PasswordBO;
import com.puhj.rye.bo.UserBO;
import com.puhj.rye.common.constant.Result;
import com.puhj.rye.common.exception.HttpException;
import com.puhj.rye.common.utils.ContrastUtil;
import com.puhj.rye.common.utils.DateUtil;
import com.puhj.rye.common.utils.SubjectUtil;
import com.puhj.rye.dto.UserDTO;
import com.puhj.rye.entity.User;
import com.puhj.rye.mapper.UserMapper;
import com.puhj.rye.service.UserService;
import com.puhj.rye.vo.FileVO;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.UserBasicInfoVO;
import com.puhj.rye.vo.UserInfoVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Transactional
    @Override
    public boolean add(UserDTO userDTO) {
        User user = userDTO.entity();

        // 新增用户
        if (this.userMapper.insert(user) <= 0) {
            throw new HttpException(Result.USER_ADD_ERROR);
        }

        // 分配角色
        if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
            if (this.userMapper.insertRoleIdsByUserId(user.getId(), userDTO.getRoles()) <= 0) {
                throw new HttpException(Result.USER_SET_ROLES_ERROR);
            }
        }

        // 分配岗位
        if (userDTO.getPosts() != null && !userDTO.getPosts().isEmpty()) {
            if (this.userMapper.insertPostIdsByUserId(user.getId(), userDTO.getPosts()) <= 0) {
                throw new HttpException(Result.USER_SET_POSTS_ERROR);
            }
        }

        return true;
    }

    @Transactional
    @Override
    public boolean edit(UserDTO userDTO) {
        User user = userDTO.entity();

        // 编辑用户
        if (this.userMapper.updateById(user) <= 0) {
            throw new HttpException(Result.USER_EDIT_ERROR);
        }

        // 分配角色
        List<Integer> roleIds = this.userMapper.selectRoleIdsByUserId(userDTO.getId());
        Map<String, List<Integer>> roleMap = ContrastUtil.getContrast(roleIds, userDTO.getRoles());
        if (!roleMap.get("remove").isEmpty()) {
            // 清除被移除的角色
            if (!this.userMapper.deleteRoleIdsByUserId(userDTO.getId(), roleMap.get("remove"))) {
                throw new HttpException(Result.USER_SET_ROLES_ERROR);
            }
        }
        if (!roleMap.get("add").isEmpty()) {
            // 添加新分配的角色
            if (this.userMapper.insertRoleIdsByUserId(userDTO.getId(), roleMap.get("add")) <= 0) {
                throw new HttpException(Result.USER_SET_ROLES_ERROR);
            }
        }

        // 分配岗位
        List<Integer> postIds = this.userMapper.selectPostIdsByUserId(userDTO.getId());
        Map<String, List<Integer>> postMap = ContrastUtil.getContrast(postIds, userDTO.getPosts());
        if (!postMap.get("remove").isEmpty()) {
            // 清除被移除的岗位
            if (!this.userMapper.deletePostIdsByUserId(userDTO.getId(), postMap.get("remove"))) {
                throw new HttpException(Result.USER_SET_POSTS_ERROR);
            }
        }
        if (!postMap.get("add").isEmpty()) {
            // 添加新分配的岗位
            if (this.userMapper.insertPostIdsByUserId(userDTO.getId(), postMap.get("add")) <= 0) {
                throw new HttpException(Result.USER_SET_POSTS_ERROR);
            }
        }

        return true;
    }

    @Override
    public User getByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).isNull("delete_time");
        return this.userMapper.selectOne(queryWrapper);
    }

    @Override
    public UserBasicInfoVO getBasicInfo() {
        return this.userMapper.selectBasicInfo(SubjectUtil.getSubjectId());
    }

    @Override
    public boolean editBasicInfo(UserDTO userDTO) {
        User user = userDTO.entity();

        // 编辑基本用户信息
        if (this.userMapper.updateById(user) <= 0) {
            throw new HttpException(Result.USER_EDIT_ERROR);
        }
        return true;
    }

    @Override
    public PageVO<UserInfoVO> list(Page<UserInfoVO> page, String username, String name, String phone, String email) {
        Page<UserInfoVO> pageList = this.userMapper.list(page, username, name, phone, email);
        return new PageVO<>(pageList);
    }

    @Override
    public int updatePassword(PasswordBO passwordBO) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", passwordBO.getUserId())
                .set("password", passwordBO.getNewPassword())
                .set("update_time", DateUtil.getLocalDateTime(new Date()));
        return this.userMapper.update(null, updateWrapper);
    }

    @Override
    public String modifyAvatar(User user, FileVO avatar) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", user.getId())
                .set("avatar", avatar.getPath())
                .set("update_user", user.getId())
                .set("update_time", DateUtil.getLocalDateTime(new Date()));
        this.userMapper.update(null, updateWrapper);
        return avatar.getPath();
    }

    @Override
    public List<UserBO> getOptions() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_status", "0").isNull("delete_time");
        List<User> users = this.userMapper.selectList(queryWrapper);
        return users.stream().map(user -> {
            UserBO userBO = new UserBO();
            userBO.setId(user.getId());
            userBO.setUsername(user.getUsername());
            userBO.setName(user.getName());
            return userBO;
        }).toList();
    }

}
