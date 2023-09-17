package com.puhj.rye.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puhj.rye.bo.PermissionBO;
import com.puhj.rye.vo.PermissionInfoVO;
import com.puhj.rye.dto.PermissionDTO;
import com.puhj.rye.entity.Permission;
import com.puhj.rye.vo.PageVO;

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

    boolean add(PermissionDTO permissionDTO);

    boolean edit(PermissionDTO permissionDTO);

    PageVO<PermissionInfoVO> list(Page<PermissionInfoVO> page, String code, String name, String menu);

    List<PermissionBO> getOptions();

}
