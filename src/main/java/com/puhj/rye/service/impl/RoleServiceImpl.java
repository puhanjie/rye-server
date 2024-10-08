package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.bo.RoleBO;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.common.constant.Result;
import com.puhj.rye.common.exception.HttpException;
import com.puhj.rye.common.utils.ContrastUtil;
import com.puhj.rye.dto.RoleDTO;
import com.puhj.rye.entity.Role;
import com.puhj.rye.mapper.RoleMapper;
import com.puhj.rye.service.RoleService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.RoleInfoVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Transactional
    @Override
    public boolean add(RoleDTO roleDTO) {
        Role role = roleDTO.entity();

        // 新增角色
        if (this.roleMapper.insert(role) <= 0) {
            throw new HttpException(Result.ROLE_ADD_ERROR);
        }

        // 分配权限
        if (Permissions.ADMIN.equals(roleDTO.getCode())) {
            // 若为admin角色则无需分配权限
            return true;
        }
        if (roleDTO.getPermissions() != null && !roleDTO.getPermissions().isEmpty()) {
            if (this.roleMapper.insertPermissionIdsByRoleId(role.getId(), roleDTO.getPermissions()) <= 0) {
                throw new HttpException(Result.ROLE_SET_PERMISSIONS_ERROR);
            }
        }

        return true;
    }

    @Transactional
    @Override
    public boolean edit(RoleDTO roleDTO) {
        Role role = roleDTO.entity();

        // 编辑角色
        if (this.roleMapper.updateById(role) <= 0) {
            throw new HttpException(Result.ROLE_EDIT_ERROR);
        }

        // 分配权限
        if (Permissions.ADMIN.equals(roleDTO.getCode())) {
            // 若为admin角色则无需分配权限
            return true;
        }
        List<Integer> permissionIds = this.roleMapper.selectPermissionIdsByRoleId(roleDTO.getId());
        Map<String, List<Integer>> permissionMap = ContrastUtil.getContrast(permissionIds, roleDTO.getPermissions());
        if (!permissionMap.get("remove").isEmpty()) {
            // 清除被移除的权限
            if (!this.roleMapper.deletePermissionIdsByRoleId(roleDTO.getId(), permissionMap.get("remove"))) {
                throw new HttpException(Result.ROLE_SET_PERMISSIONS_ERROR);
            }
        }
        if (!permissionMap.get("add").isEmpty()) {
            // 添加新分配的权限
            if (this.roleMapper.insertPermissionIdsByRoleId(roleDTO.getId(), permissionMap.get("add")) <= 0) {
                throw new HttpException(Result.ROLE_SET_PERMISSIONS_ERROR);
            }
        }

        return true;
    }

    @Override
    public PageVO<RoleInfoVO> list(Page<RoleInfoVO> page, String code, String name) {
        Page<RoleInfoVO> pageList = this.roleMapper.list(page, code, name);
        return new PageVO<>(pageList);
    }

    @Override
    public List<RoleBO> getOptions() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_status", "0").isNull("delete_time");
        List<Role> roles = this.roleMapper.selectList(queryWrapper);
        return roles.stream().map(role -> {
            RoleBO roleBO = new RoleBO();
            roleBO.setId(role.getId());
            roleBO.setCode(role.getCode());
            roleBO.setName(role.getName());
            return roleBO;
        }).toList();
    }

}
