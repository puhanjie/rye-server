package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.dto.RoleDTO;
import com.puhj.rye.entity.Role;
import com.puhj.rye.entity.RolePermission;
import com.puhj.rye.mapper.RoleMapper;
import com.puhj.rye.service.RolePermissionService;
import com.puhj.rye.service.RoleService;
import com.puhj.rye.service.UserRoleService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.RoleListVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleMapper roleMapper;

    private final UserRoleService userRoleService;

    private final RolePermissionService rolePermissionService;

    public RoleServiceImpl(RoleMapper roleMapper, UserRoleService userRoleService, RolePermissionService rolePermissionService) {
        this.roleMapper = roleMapper;
        this.userRoleService = userRoleService;
        this.rolePermissionService = rolePermissionService;
    }

    @Override
    public boolean add(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        role.setInfo(roleDTO.getInfo());
        int count = this.roleMapper.insert(role);
        if (count <= 0) {
            return false;
        }

        if (roleDTO.getPermissions() != null) {
            // 给新增角色分配权限
            QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", roleDTO.getName());
            Integer roleId = this.roleMapper.selectOne(queryWrapper).getId();
            List<RolePermission> rolePermissions = roleDTO.getPermissions().stream().map(permissionId -> {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(roleId);
                rolePermission.setPermissionId(permissionId);
                return rolePermission;
            }).toList();
            return this.rolePermissionService.saveBatch(rolePermissions);
        }

        return true;
    }

    @Override
    public boolean edit(RoleDTO roleDTO) {
        Role role = this.roleMapper.selectById(roleDTO.getId());
        UpdateWrapper<Role> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", roleDTO.getId())
                .set("name", roleDTO.getName())
                .set("info", roleDTO.getInfo());
        int count = this.roleMapper.update(role, updateWrapper);
        // 分配权限
        boolean flag = this.rolePermissionService.setPermissionsByRoleId(roleDTO.getId(), roleDTO.getPermissions());
        return count > 0 && flag;
    }

    @Override
    public List<Role> getListByUserId(Integer userId) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", this.userRoleService.getRoleIdsByUserId(userId));
        return this.roleMapper.selectList(queryWrapper);
    }

    @Override
    public PageVO<RoleListVO> list(Page<RoleListVO> page, String name, String info) {
        Page<RoleListVO> pageList = this.roleMapper.list(page, name, info);
        return new PageVO<>(pageList);
    }

}
