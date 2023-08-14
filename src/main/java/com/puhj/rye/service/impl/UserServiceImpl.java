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
import com.puhj.rye.service.*;
import com.puhj.rye.vo.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    private final FileService fileService;

    public UserServiceImpl(UserMapper userMapper,
                           RoleService roleService,
                           PermissionService permissionService,
                           UserRoleService userRoleService,
                           FileService fileService) {
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.userRoleService = userRoleService;
        this.fileService = fileService;
    }

    @Transactional
    @Override
    public boolean add(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setNickname(userDTO.getNickname());
        user.setUserStatus(userDTO.getUserStatus());
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

    @Transactional
    @Override
    public boolean edit(UserDTO userDTO) {
        User user = this.userMapper.selectById(userDTO.getId());
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", userDTO.getId())
                .set("username", userDTO.getUsername())
                .set("nickname", userDTO.getNickname())
                .set("user_status", userDTO.getUserStatus())
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
    public PageVO<UserListVO> list(Page<UserListVO> page, String username, String phone, String email) {
        Page<UserListVO> pageList = this.userMapper.list(page, username, phone, email);
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
    public AvatarVO modifyAvatar(MultipartFile[] files, HttpServletRequest request) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        String token = subject.getPrincipal().toString();
        User user = this.getByUsername(JwtUtil.getTokenInfo(token));

        // 删除原有头像文件
        if (!user.getAvatar().isBlank()) {
            this.fileService.remove(user.getAvatar(), request);
        }
        FileVO avatar = this.fileService.upload(files, request).get(0);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", user.getId()).set("avatar", avatar.getFilePath());
        this.userMapper.update(user, updateWrapper);
        return new AvatarVO(avatar.getFilePath());
    }

}
