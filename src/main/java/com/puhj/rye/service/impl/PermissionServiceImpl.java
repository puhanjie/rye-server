package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.dto.PermissionPageDTO;
import com.puhj.rye.entity.Permission;
import com.puhj.rye.entity.Role;
import com.puhj.rye.mapper.PermissionMapper;
import com.puhj.rye.service.PermissionService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.PermissionListVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public PermissionServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<Permission> getListByRoles(List<Role> roles) {
        List<Permission> permissions = new ArrayList<>();
        roles.forEach(role -> permissions.addAll(this.permissionMapper.selectListByRoleId(role.getId())));
        return permissions;
    }

    @Override
    public List<PermissionListVO> getAll() {
        return this.permissionMapper.selectAll();
    }

    @Override
    public PageVO<PermissionListVO> getPageList(Page<PermissionListVO> page, PermissionPageDTO permissionPageDTO) {
        Page<PermissionListVO> pageList = this.permissionMapper.selectPageList(page, permissionPageDTO);
        return new PageVO<>(pageList);
    }

}
