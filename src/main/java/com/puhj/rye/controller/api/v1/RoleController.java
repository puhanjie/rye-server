package com.puhj.rye.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.bo.DictionaryBO;
import com.puhj.rye.bo.PermissionBO;
import com.puhj.rye.vo.RoleInfoVO;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.dto.RoleDTO;
import com.puhj.rye.service.DictionaryService;
import com.puhj.rye.service.PermissionService;
import com.puhj.rye.service.RoleService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.RoleOptionsVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "角色接口", description = "角色操作相关接口")
@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    private final RoleService roleService;

    private final PermissionService permissionService;

    private final DictionaryService dictionaryService;

    public RoleController(RoleService roleService,
            PermissionService permissionService,
            DictionaryService dictionaryService) {
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.dictionaryService = dictionaryService;
    }

    @Operation(summary = "新增角色", description = "新增一个角色")
    @PostMapping
    @RequiresPermissions(Permissions.Role.ADD)
    public boolean add(@RequestBody RoleDTO roleDTO) {
        return this.roleService.add(roleDTO);
    }

    @Operation(summary = "删除角色", description = "根据角色id数组删除角色")
    @DeleteMapping
    @RequiresPermissions(Permissions.Role.DELETE)
    public boolean remove(@RequestBody List<Integer> ids) {
        return this.roleService.removeByIds(ids);
    }

    @Operation(summary = "编辑角色", description = "编辑角色信息")
    @PutMapping
    @RequiresPermissions(Permissions.Role.EDIT)
    public boolean edit(@RequestBody RoleDTO roleDTO) {
        return this.roleService.edit(roleDTO);
    }

    @Operation(summary = "查询角色列表", description = "分页查询角色列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "分页查询页码"),
            @Parameter(name = "pageSize", description = "每页数据大小"),
            @Parameter(name = "code", description = "角色编码"),
            @Parameter(name = "name", description = "角色名称")
    })
    @GetMapping("/list")
    @RequiresPermissions(Permissions.Role.VIEW)
    public PageVO<RoleInfoVO> list(
            @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name) {
        Page<RoleInfoVO> page = new Page<>(pageNum, pageSize);
        return this.roleService.list(page, code, name);
    }

    @Operation(summary = "获取选项数据", description = "获取角色表单选项及字典数据")
    @GetMapping("/options")
    @RequiresPermissions(Permissions.Role.VIEW)
    public RoleOptionsVO getRoleOptions() {
        List<DictionaryBO> roleStatus = this.dictionaryService.getItems("role_status");
        List<PermissionBO> permissions = this.permissionService.getOptions();
        return new RoleOptionsVO(roleStatus, permissions);
    }

}
