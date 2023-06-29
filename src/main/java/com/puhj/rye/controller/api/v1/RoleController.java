package com.puhj.rye.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.dto.IdsDTO;
import com.puhj.rye.dto.RolePageDTO;
import com.puhj.rye.entity.Role;
import com.puhj.rye.service.RoleService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.RoleListVO;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.Role.ADD}, logical = Logical.OR)
    public boolean add(@RequestBody Role role) {
        return this.roleService.save(role);
    }

    @DeleteMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.Role.DELETE}, logical = Logical.OR)
    public boolean remove(@RequestBody IdsDTO idsDTO) {
        return this.roleService.removeByIds(idsDTO.getIds());
    }

    @PutMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.Role.UPDATE}, logical = Logical.OR)
    public boolean edit(@RequestBody Role role) {
        return this.roleService.updateById(role);
    }

    @GetMapping("/all")
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.Role.VIEW}, logical = Logical.OR)
    public List<RoleListVO> getAll() {
        return this.roleService.getAll();
    }

    @GetMapping("/list")
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.Role.VIEW}, logical = Logical.OR)
    public PageVO<RoleListVO> getPageList(RolePageDTO rolePageDTO) {
        Page<RoleListVO> page = new Page<>(rolePageDTO.getPageNum(), rolePageDTO.getPageSize());
        return this.roleService.getPageList(page, rolePageDTO);
    }

}
