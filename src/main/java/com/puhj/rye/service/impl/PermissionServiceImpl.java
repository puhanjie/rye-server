package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.bo.PermissionBO;
import com.puhj.rye.common.constant.ResultCode;
import com.puhj.rye.common.exception.HttpException;
import com.puhj.rye.common.utils.SubjectUtil;
import com.puhj.rye.dto.PermissionDTO;
import com.puhj.rye.entity.Permission;
import com.puhj.rye.mapper.PermissionMapper;
import com.puhj.rye.service.PermissionService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.PermissionInfoVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public boolean add(PermissionDTO permissionDTO) {
        Integer currentUserId = SubjectUtil.getSubjectId();
        Permission permission = permissionDTO.entity();
        permission.setCreateUser(currentUserId);
        permission.setUpdateUser(currentUserId);

        // 新增权限
        if (this.permissionMapper.insert(permission) <= 0) {
            throw new HttpException(ResultCode.PERMISSION_ADD_ERROR);
        }

        return true;
    }

    @Transactional
    @Override
    public boolean edit(PermissionDTO permissionDTO) {
        Permission permission = permissionDTO.entity();
        permission.setUpdateUser(SubjectUtil.getSubjectId());

        // 编辑权限
        if (this.permissionMapper.updateById(permission) <= 0) {
            throw new HttpException(ResultCode.PERMISSION_EDIT_ERROR);
        }

        return true;
    }

    @Override
    public PageVO<PermissionInfoVO> list(Page<PermissionInfoVO> page, String code, String name, String menu) {
        Page<PermissionInfoVO> pageList = this.permissionMapper.list(page, code, name, menu);
        return new PageVO<>(pageList);
    }

    @Override
    public List<PermissionBO> getOptions() {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("permission_status", "0").isNull("delete_time");
        List<Permission> permissions = this.permissionMapper.selectList(queryWrapper);
        return permissions.stream().map(permission -> {
            PermissionBO permissionBO = new PermissionBO();
            permissionBO.setId(permission.getId());
            permissionBO.setCode(permission.getCode());
            permissionBO.setName(permission.getName());
            permissionBO.setMenu(permission.getMenu());
            return permissionBO;
        }).toList();
    }

}
