package com.puhj.rye.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.dto.IdsDTO;
import com.puhj.rye.dto.PermissionPageDTO;
import com.puhj.rye.entity.Permission;
import com.puhj.rye.service.PermissionService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.PermissionListVO;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.Permission.ADD}, logical = Logical.OR)
    public boolean add(@RequestBody Permission permission) {
        return this.permissionService.save(permission);
    }

    @DeleteMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.Permission.DELETE}, logical = Logical.OR)
    public boolean remove(@RequestBody IdsDTO idsDTO) {
        return this.permissionService.removeByIds(idsDTO.getIds());
    }

    @PutMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.Permission.UPDATE}, logical = Logical.OR)
    public boolean edit(@RequestBody Permission permission) {
        return this.permissionService.updateById(permission);
    }

    @GetMapping("/list")
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.Permission.VIEW}, logical = Logical.OR)
    public PageVO<PermissionListVO> getPageList(PermissionPageDTO permissionPageDTO) {
        Page<PermissionListVO> page = new Page<>(permissionPageDTO.getPageNum(), permissionPageDTO.getPageSize());
        return this.permissionService.getPageList(page, permissionPageDTO);
    }

}
