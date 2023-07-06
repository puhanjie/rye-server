package com.puhj.rye.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puhj.rye.dto.RolePageDTO;
import com.puhj.rye.entity.Role;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.RoleListVO;

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

    List<Role> getListByUserId(Integer userId);

    PageVO<RoleListVO> getPageList(Page<RoleListVO> page, RolePageDTO rolePageDTO);

}
