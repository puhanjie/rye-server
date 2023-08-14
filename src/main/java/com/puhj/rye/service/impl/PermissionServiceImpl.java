package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.entity.Permission;
import com.puhj.rye.entity.Role;
import com.puhj.rye.mapper.PermissionMapper;
import com.puhj.rye.service.PermissionService;
import com.puhj.rye.service.RolePermissionService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.PermissionListVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    private final PermissionMapper permissionMapper;

    private final RolePermissionService rolePermissionService;

    public PermissionServiceImpl(PermissionMapper permissionMapper, RolePermissionService rolePermissionService) {
        this.permissionMapper = permissionMapper;
        this.rolePermissionService = rolePermissionService;
    }

    @Override
    public List<Permission> getListByRoles(List<Role> roles) {
        if (roles == null) {
            return null;
        }
        List<Integer> roleIds = roles.stream().map(Role::getId).toList();
        List<Integer> permissionIds = this.rolePermissionService.getPermissionIdsByRoleIds(roleIds);
        // 角色没分配权限
        if (permissionIds.isEmpty()) {
            return null;
        }
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", permissionIds);
        return this.permissionMapper.selectList(queryWrapper);
    }

    @Override
    public PageVO<PermissionListVO> list(Page<PermissionListVO> page, String name, String info, String menu) {
        Page<PermissionListVO> pageList = this.permissionMapper.list(page, name, info, menu);
        return new PageVO<>(pageList);
    }

}
