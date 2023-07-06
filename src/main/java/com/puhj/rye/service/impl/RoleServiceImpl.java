package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.dto.RolePageDTO;
import com.puhj.rye.entity.Role;
import com.puhj.rye.mapper.RoleMapper;
import com.puhj.rye.service.RoleService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.RoleListVO;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Role> getListByUserId(Integer userId) {
        return this.roleMapper.selectListByUserId(userId);
    }

    @Override
    public PageVO<RoleListVO> getPageList(Page<RoleListVO> page, RolePageDTO rolePageDTO) {
        Page<RoleListVO> pageList = this.roleMapper.selectPageList(page, rolePageDTO);
        return new PageVO<>(pageList);
    }

}
