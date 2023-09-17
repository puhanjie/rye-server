package com.puhj.rye.controller.api.v1;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.bo.DictionaryBO;
import com.puhj.rye.vo.PermissionInfoVO;
import com.puhj.rye.vo.PermissionOptionsVO;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.dto.PermissionDTO;
import com.puhj.rye.service.DictionaryService;
import com.puhj.rye.service.PermissionService;
import com.puhj.rye.vo.PageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    private final DictionaryService dictionaryService;

    public PermissionController(PermissionService permissionService,
                                DictionaryService dictionaryService) {
        this.permissionService = permissionService;
        this.dictionaryService = dictionaryService;
    }

    @Operation(summary = "新增权限", description = "新增一个权限")
    @PostMapping
    @RequiresPermissions(Permissions.Permission.ADD)
    public boolean add(@RequestBody PermissionDTO permissionDTO) {
        return this.permissionService.add(permissionDTO);
    }

    @Operation(summary = "删除权限", description = "根据权限id数组删除权限")
    @DeleteMapping
    @RequiresPermissions(Permissions.Permission.DELETE)
    public boolean remove(@RequestBody List<Integer> ids) {
        return this.permissionService.removeByIds(ids);
    }

    @Operation(summary = "编辑权限", description = "编辑权限信息")
    @PutMapping
    @RequiresPermissions(Permissions.Permission.EDIT)
    public boolean edit(@RequestBody PermissionDTO permissionDTO) {
        return this.permissionService.edit(permissionDTO);
    }

    @Operation(summary = "查询权限列表", description = "分页查询权限列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "分页查询页码"),
            @Parameter(name = "pageSize", description = "每页数据大小"),
            @Parameter(name = "code", description = "权限编码"),
            @Parameter(name = "name", description = "权限名称"),
            @Parameter(name = "menu", description = "菜单")
    })
    @GetMapping("/list")
    @RequiresPermissions(Permissions.Permission.VIEW)
    public PageVO<PermissionInfoVO> list(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                         @RequestParam(value = "code", required = false) String code,
                                         @RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "menu", required = false) String menu) {
        Page<PermissionInfoVO> page = new Page<>(pageNum, pageSize);
        return this.permissionService.list(page, code, name, menu);
    }

    @Operation(summary = "获取选项数据", description = "获取权限表单选项及字典数据")
    @GetMapping("/options")
    @RequiresPermissions(Permissions.Permission.VIEW)
    public PermissionOptionsVO getPermissionOptions() {
        List<DictionaryBO> permissionStatus = this.dictionaryService.getItems("permission_status");
        return new PermissionOptionsVO(permissionStatus);
    }

}
