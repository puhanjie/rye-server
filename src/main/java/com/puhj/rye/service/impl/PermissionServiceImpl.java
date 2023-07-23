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
import java.util.stream.Collectors;

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
        List<Integer> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", this.rolePermissionService.getPermissionIdsByRoleIds(roleIds));
        return this.permissionMapper.selectList(queryWrapper);
    }

    @Override
    public PageVO<PermissionListVO> getPageList(Page<PermissionListVO> page, String name, String info, String menuName) {
        Page<PermissionListVO> pageList = this.permissionMapper.selectPageList(page, name, info, menuName);
        return new PageVO<>(pageList);
    }

}
