package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.bo.PasswordBO;
import com.puhj.rye.common.utils.JwtUtil;
import com.puhj.rye.dto.UserDTO;
import com.puhj.rye.entity.Permission;
import com.puhj.rye.entity.Role;
import com.puhj.rye.entity.User;
import com.puhj.rye.entity.UserRole;
import com.puhj.rye.mapper.UserMapper;
import com.puhj.rye.service.PermissionService;
import com.puhj.rye.service.RoleService;
import com.puhj.rye.service.UserRoleService;
import com.puhj.rye.service.UserService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.UserInfoVO;
import com.puhj.rye.vo.UserListVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private final RoleService roleService;

    private final PermissionService permissionService;

    private final UserRoleService userRoleService;

    public UserServiceImpl(UserMapper userMapper,
                           RoleService roleService,
                           PermissionService permissionService,
                           UserRoleService userRoleService) {
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.userRoleService = userRoleService;
    }

    @Override
    public boolean add(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setAvatar(userDTO.getAvatar());
        user.setEmail(userDTO.getEmail());
        int count = this.userMapper.insert(user);
        if (count <= 0) {
            return false;
        }

        if (userDTO.getRoles() != null) {
            // 给新增用户分配角色
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", userDTO.getUsername());
            Integer userId = this.userMapper.selectOne(queryWrapper).getId();
            List<UserRole> userRoles = userDTO.getRoles().stream().map(roleId -> {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                return userRole;
            }).toList();
            return this.userRoleService.saveBatch(userRoles);
        }

        return true;
    }

    @Override
    public boolean edit(UserDTO userDTO) {
        User user = this.userMapper.selectById(userDTO.getId());
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", userDTO.getId())
                .set("username", userDTO.getUsername())
                .set("phone", userDTO.getPhone())
                .set("avatar", userDTO.getAvatar())
                .set("email", userDTO.getEmail());
        int count = this.userMapper.update(user, updateWrapper);
        // 分配角色
        boolean flag = this.userRoleService.setRolesByUserId(userDTO.getId(), userDTO.getRoles());
        return count > 0 && flag;
    }

    @Override
    public User getByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return this.userMapper.selectOne(queryWrapper);
    }

    @Override
    public UserInfoVO getInfo() {
        Subject subject = SecurityUtils.getSubject();
        String token = subject.getPrincipal().toString();
        User user = this.getByUsername(JwtUtil.getTokenInfo(token));

        List<Role> userRoles = roleService.getListByUserId(user.getId());
        List<Permission> userPermissions = permissionService.getListByRoles(userRoles);
        return new UserInfoVO(user, userRoles, userPermissions);
    }

    @Override
    public PageVO<UserListVO> query(Page<UserListVO> page, String username, String phone, String email) {
        Page<UserListVO> pageList = this.userMapper.query(page, username, phone, email);
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

}
