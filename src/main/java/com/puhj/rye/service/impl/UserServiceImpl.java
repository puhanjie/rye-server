package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.bo.PasswordBO;
import com.puhj.rye.bo.UserBO;
import com.puhj.rye.common.constant.ResultCode;
import com.puhj.rye.common.exception.HttpException;
import com.puhj.rye.common.utils.ContrastUtil;
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
        Integer currentUserId = SubjectUtil.getSubjectId();
        User user = new User();
        user.setDepartment(userDTO.getDepartment());
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setSex(userDTO.getSex());
        user.setUserStatus(userDTO.getUserStatus());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setAvatar(userDTO.getAvatar());
        user.setEmail(userDTO.getEmail());
        user.setCreateUser(currentUserId);
        user.setUpdateUser(currentUserId);

        // 新增用户
        if (this.userMapper.insert(user) <= 0) {
            throw new HttpException(ResultCode.USER_ADD_ERROR);
        }

        // 分配角色
        if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
            if (this.userMapper.insertRoleIdsByUserId(user.getId(), userDTO.getRoles()) <= 0) {
                throw new HttpException(ResultCode.USER_SET_ROLES_ERROR);
            }
        }

        // 分配岗位
        if (userDTO.getPosts() != null && !userDTO.getPosts().isEmpty()) {
            if (this.userMapper.insertPostIdsByUserId(user.getId(), userDTO.getPosts()) <= 0) {
                throw new HttpException(ResultCode.USER_SET_POSTS_ERROR);
            }
        }

        return true;
    }

    @Transactional
    @Override
    public boolean edit(UserDTO userDTO) {
        User user = this.userMapper.selectById(userDTO.getId());
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", userDTO.getId())
                .set("department", userDTO.getDepartment())
                .set("username", userDTO.getUsername())
                .set("name", userDTO.getName())
                .set("sex", userDTO.getSex())
                .set("user_status", userDTO.getUserStatus())
                .set("phone", userDTO.getPhone())
                .set("email", userDTO.getEmail())
                .set("update_user", SubjectUtil.getSubjectId());

        // 编辑用户
        if (this.userMapper.update(user, updateWrapper) <= 0) {
            throw new HttpException(ResultCode.USER_EDIT_ERROR);
        }

        // 分配角色
        List<Integer> roleIds = this.userMapper.selectRoleIdsByUserId(userDTO.getId());
        Map<String, List<Integer>> roleMap = ContrastUtil.getContrast(roleIds, userDTO.getRoles());
        if (!roleMap.get("remove").isEmpty()) {
            // 清除被移除的角色
            if (!this.userMapper.deleteRoleIdsByUserId(userDTO.getId(), roleMap.get("remove"))) {
                throw new HttpException(ResultCode.USER_SET_ROLES_ERROR);
            }
        }
        if (!roleMap.get("add").isEmpty()) {
            // 添加新分配的角色
            if (this.userMapper.insertRoleIdsByUserId(userDTO.getId(), roleMap.get("add")) <= 0) {
                throw new HttpException(ResultCode.USER_SET_ROLES_ERROR);
            }
        }

        // 分配岗位
        List<Integer> postIds = this.userMapper.selectPostIdsByUserId(userDTO.getId());
        Map<String, List<Integer>> postMap = ContrastUtil.getContrast(postIds, userDTO.getPosts());
        if (!postMap.get("remove").isEmpty()) {
            // 清除被移除的岗位
            if (!this.userMapper.deletePostIdsByUserId(userDTO.getId(), postMap.get("remove"))) {
                throw new HttpException(ResultCode.USER_SET_POSTS_ERROR);
            }
        }
        if (!postMap.get("add").isEmpty()) {
            // 添加新分配的岗位
            if (this.userMapper.insertPostIdsByUserId(userDTO.getId(), postMap.get("add")) <= 0) {
                throw new HttpException(ResultCode.USER_SET_POSTS_ERROR);
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
    public PageVO<UserInfoVO> list(Page<UserInfoVO> page, String username, String name, String phone, String email) {
        Page<UserInfoVO> pageList = this.userMapper.list(page, username, name, phone, email);
        return new PageVO<>(pageList);
    }

    @Override
    public int updatePassword(PasswordBO passwordBO) {
        User user = this.userMapper.selectById(passwordBO.getUserId());
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", passwordBO.getUserId());
        updateWrapper.set("password", passwordBO.getNewPassword());
        return this.userMapper.update(user, updateWrapper);
    }

    @Override
    public String modifyAvatar(User user, FileVO avatar) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", user.getId()).set("avatar", avatar.getFilePath());
        this.userMapper.update(user, updateWrapper);
        return avatar.getFilePath();
    }

    @Override
    public List<UserBO> getOptions() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("delete_time");
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
