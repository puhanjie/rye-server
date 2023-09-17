package com.puhj.rye.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puhj.rye.bo.RoleBO;
import com.puhj.rye.dto.RoleDTO;
import com.puhj.rye.entity.Role;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.RoleInfoVO;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
public interface RoleService extends IService<Role> {

    boolean add(RoleDTO roleDTO);

    boolean edit(RoleDTO roleDTO);

    PageVO<RoleInfoVO> list(Page<RoleInfoVO> page, String code, String name);

    List<RoleBO> getOptions();

}
