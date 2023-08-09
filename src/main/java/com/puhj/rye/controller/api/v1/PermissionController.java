package com.puhj.rye.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.entity.Permission;
import com.puhj.rye.service.PermissionService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.PermissionListVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Tag(name = "权限接口", description = "权限操作相关接口")
@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Operation(summary = "新增权限", description = "新增一个权限")
    @PostMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.Permission.ADD}, logical = Logical.OR)
    public boolean add(@RequestBody Permission permission) {
        return this.permissionService.save(permission);
    }

    @Operation(summary = "删除权限", description = "根据权限id数组删除权限")
    @DeleteMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.Permission.DELETE, Permissions.Permission.BATCHDELETE}, logical = Logical.OR)
    public boolean remove(@RequestBody List<Integer> ids) {
        return this.permissionService.removeByIds(ids);
    }

    @Operation(summary = "修改权限", description = "修改权限信息")
    @PutMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.Permission.UPDATE}, logical = Logical.OR)
    public boolean edit(@RequestBody Permission permission) {
        return this.permissionService.updateById(permission);
    }

    @Operation(summary = "查询权限列表", description = "分页查询权限列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "分页查询页码"),
            @Parameter(name = "pageSize", description = "每页数据大小"),
            @Parameter(name = "name", description = "权限名"),
            @Parameter(name = "info", description = "权限信息"),
            @Parameter(name = "menu", description = "菜单")
    })
    @GetMapping("/list")
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.Permission.VIEW}, logical = Logical.OR)
    public PageVO<PermissionListVO> list(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                         @RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "info", required = false) String info,
                                         @RequestParam(value = "menu", required = false) String menu) {
        Page<PermissionListVO> page = new Page<>(pageNum, pageSize);
        return this.permissionService.list(page, name, info, menu);
    }

    @Operation(summary = "查询所有权限", description = "查询所有权限数据")
    @GetMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.Permission.VIEW}, logical = Logical.OR)
    public List<Permission> query() {
        return this.permissionService.list();
    }

}
