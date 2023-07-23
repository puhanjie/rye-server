package com.puhj.rye.service;

import com.puhj.rye.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 * @author puhanjie
 * @since 2023-07-22
 */
public interface RolePermissionService extends IService<RolePermission> {

    boolean setPermissionsByRoleId(Integer roleId, List<Integer> permissionIds);

    List<Integer> getPermissionIdsByRoleIds(List<Integer> roleIds);

}
