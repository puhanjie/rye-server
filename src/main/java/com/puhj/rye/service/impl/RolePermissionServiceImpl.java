package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.entity.RolePermission;
import com.puhj.rye.mapper.RolePermissionMapper;
import com.puhj.rye.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author puhanjie
 * @since 2023-07-22
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    private final RolePermissionMapper rolePermissionMapper;

    public RolePermissionServiceImpl(RolePermissionMapper rolePermissionMapper) {
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public boolean setPermissionsByRoleId(Integer roleId, List<Integer> permissionIds) {
        // 获取当前角色权限
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct permission_id").eq("role_id", roleId);
        List<Integer> permissions = this.rolePermissionMapper.selectList(queryWrapper)
                .stream()
                .map(RolePermission::getPermissionId)
                .toList();

        try {
            List<Integer> removePermissions = new ArrayList<>();
            // 被移除的权限
            for (Integer permissionId : permissions) {
                if (!permissionIds.contains(permissionId)) {
                    removePermissions.add(permissionId);
                }
            }
            if (!removePermissions.isEmpty()) {
                queryWrapper.clear();
                queryWrapper.eq("role_id", roleId).in("permission_id", removePermissions);
                if (this.rolePermissionMapper.delete(queryWrapper) < 0) {
                    return false;
                }
            }

            // 新分配权限
            for (Integer permissionId : permissionIds) {
                if (!permissions.contains(permissionId)) {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRoleId(roleId);
                    rolePermission.setPermissionId(permissionId);
                    if (this.rolePermissionMapper.insert(rolePermission) < 0) {
                        return false;
                    }
                }
            }
        } catch (NullPointerException e) {
            // 若permissionIds为空且角色有权限数据时,则清空权限
            if (!permissions.isEmpty() && permissionIds == null) {
                queryWrapper.clear();
                queryWrapper.eq("role_id", roleId);
                return this.rolePermissionMapper.delete(queryWrapper) >= 0;
            }
            return false;
        }
        return true;
    }

    @Override
    public List<Integer> getPermissionIdsByRoleIds(List<Integer> roleIds) {
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct permission_id").in("role_id", roleIds);
        // 提取查询到的RolePermission集合的permission_id作为新集合返回
        return this.rolePermissionMapper.selectList(queryWrapper)
                .stream()
                .map(RolePermission::getPermissionId)
                .toList();
    }

}
