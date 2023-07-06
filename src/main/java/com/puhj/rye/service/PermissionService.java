package com.puhj.rye.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puhj.rye.dto.PermissionPageDTO;
import com.puhj.rye.entity.Permission;
import com.puhj.rye.entity.Role;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.PermissionListVO;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
public interface PermissionService extends IService<Permission> {

    List<Permission> getListByRoles(List<Role> roles);

    PageVO<PermissionListVO> getPageList(Page<PermissionListVO> page, PermissionPageDTO permissionPageDTO);

}
