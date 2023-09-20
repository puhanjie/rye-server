package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.bo.RoleBO;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.vo.RoleInfoVO;
import com.puhj.rye.common.constant.ResultCode;
import com.puhj.rye.common.exception.HttpException;
import com.puhj.rye.common.utils.ContrastUtil;
import com.puhj.rye.common.utils.SubjectUtil;
import com.puhj.rye.dto.RoleDTO;
import com.puhj.rye.entity.Role;
import com.puhj.rye.mapper.RoleMapper;
import com.puhj.rye.service.RoleService;
import com.puhj.rye.vo.PageVO;
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
        Integer currentUserId = SubjectUtil.getSubjectId();
        Role role = new Role();
        role.setCode(roleDTO.getCode());
        role.setName(roleDTO.getName());
        role.setRoleStatus(roleDTO.getRoleStatus());
        role.setCreateUser(currentUserId);
        role.setUpdateUser(currentUserId);

        // 新增角色
        if (this.roleMapper.insert(role) <= 0) {
            throw new HttpException(ResultCode.ROLE_ADD_ERROR);
        }

        // 分配权限
        if (Permissions.ADMIN.equals(roleDTO.getCode())) {
            // 若为admin角色则无需分配权限
            return true;
        }
        if (roleDTO.getPermissions() != null && !roleDTO.getPermissions().isEmpty()) {
            if (this.roleMapper.insertPermissionIdsByRoleId(role.getId(), roleDTO.getPermissions()) <= 0) {
                throw new HttpException(ResultCode.ROLE_SET_PERMISSIONS_ERROR);
            }
        }

        return true;
    }

    @Transactional
    @Override
    public boolean edit(RoleDTO roleDTO) {
        Role role = this.roleMapper.selectById(roleDTO.getId());
        UpdateWrapper<Role> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", roleDTO.getId())
                .set("code", roleDTO.getCode())
                .set("name", roleDTO.getName())
                .set("role_status", roleDTO.getRoleStatus())
                .set("update_user", SubjectUtil.getSubjectId());

        // 编辑角色
        if (this.roleMapper.update(role, updateWrapper) <= 0) {
            throw new HttpException(ResultCode.ROLE_EDIT_ERROR);
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
                throw new HttpException(ResultCode.ROLE_SET_PERMISSIONS_ERROR);
            }
        }
        if (!permissionMap.get("add").isEmpty()) {
            // 添加新分配的权限
            if (this.roleMapper.insertPermissionIdsByRoleId(roleDTO.getId(), permissionMap.get("add")) <= 0) {
                throw new HttpException(ResultCode.ROLE_SET_PERMISSIONS_ERROR);
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
