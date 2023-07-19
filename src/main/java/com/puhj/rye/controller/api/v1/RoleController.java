package com.puhj.rye.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.dto.IdsDTO;
import com.puhj.rye.dto.RolePageDTO;
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
import org.springframework.web.bind.annotation.*;

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
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.Role.ADD}, logical = Logical.OR)
    public boolean add(@RequestBody Role role) {
        return this.roleService.save(role);
    }

    @Operation(summary = "删除角色", description = "根据角色id数组删除角色")
    @DeleteMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.Role.DELETE}, logical = Logical.OR)
    public boolean remove(@RequestBody IdsDTO idsDTO) {
        return this.roleService.removeByIds(idsDTO.getIds());
    }

    @Operation(summary = "修改角色", description = "修改角色信息")
    @PutMapping
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.Role.UPDATE}, logical = Logical.OR)
    public boolean edit(@RequestBody Role role) {
        return this.roleService.updateById(role);
    }

    @Operation(summary = "查询角色列表", description = "分页查询角色列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "分页查询页码"),
            @Parameter(name = "pageSize", description = "每页数据大小"),
            @Parameter(name = "name", description = "角色名"),
            @Parameter(name = "info", description = "角色信息")
    })
    @GetMapping("/list")
    @RequiresPermissions(value = {Permissions.ADMIN, Permissions.System.Role.VIEW}, logical = Logical.OR)
    public PageVO<RoleListVO> getPageList(RolePageDTO rolePageDTO) {
        Page<RoleListVO> page = new Page<>(rolePageDTO.getPageNum(), rolePageDTO.getPageSize());
        return this.roleService.getPageList(page, rolePageDTO);
    }

}
