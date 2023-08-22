package com.puhj.rye.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.dto.RoleDTO;
import com.puhj.rye.entity.Role;
import com.puhj.rye.service.RoleService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.RoleListVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
@Tag(name = "角色接口", description = "角色操作相关接口")
@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "新增角色", description = "新增一个角色")
    @PostMapping
    @RequiresRoles(Permissions.ADMIN)
    @RequiresPermissions(Permissions.Role.ADD)
    public boolean add(@RequestBody RoleDTO roleDTO) {
        return this.roleService.add(roleDTO);
    }

    @Operation(summary = "删除角色", description = "根据角色id数组删除角色")
    @DeleteMapping
    @RequiresRoles(Permissions.ADMIN)
    @RequiresPermissions(value = {Permissions.Role.DELETE, Permissions.Role.BATCHDELETE}, logical = Logical.OR)
    public boolean remove(@RequestBody List<Integer> ids) {
        return this.roleService.removeByIds(ids);
    }

    @Operation(summary = "编辑角色", description = "编辑角色信息")
    @PutMapping
    @RequiresRoles(Permissions.ADMIN)
    @RequiresPermissions(Permissions.Role.EDIT)
    public boolean edit(@RequestBody RoleDTO roleDTO) {
        return this.roleService.edit(roleDTO);
    }

    @Operation(summary = "查询角色列表", description = "分页查询角色列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "分页查询页码"),
            @Parameter(name = "pageSize", description = "每页数据大小"),
            @Parameter(name = "name", description = "角色名"),
            @Parameter(name = "info", description = "角色信息")
    })
    @GetMapping("/list")
    @RequiresRoles(Permissions.ADMIN)
    @RequiresPermissions(Permissions.Role.VIEW)
    public PageVO<RoleListVO> list(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                   @RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "info", required = false) String info) {
        Page<RoleListVO> page = new Page<>(pageNum, pageSize);
        return this.roleService.list(page, name, info);
    }

    @Operation(summary = "查询所有角色", description = "查询所有角色数据")
    @GetMapping
    @RequiresRoles(Permissions.ADMIN)
    @RequiresPermissions(value = {Permissions.Role.VIEW, Permissions.User.VIEW}, logical = Logical.OR)
    public List<Role> query() {
        return this.roleService.list();
    }

}
