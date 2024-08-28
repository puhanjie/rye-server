package com.puhj.rye.controller.api.v1;

import com.puhj.rye.bo.UserBO;
import com.puhj.rye.service.UserService;
import com.puhj.rye.vo.DepartmentDetailTreeVO;
import com.puhj.rye.vo.DepartmentOptionsVO;
import com.puhj.rye.bo.DictionaryBO;
import com.puhj.rye.bo.RoleBO;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.dto.DepartmentDTO;
import com.puhj.rye.service.DepartmentService;
import com.puhj.rye.service.DictionaryService;
import com.puhj.rye.service.RoleService;
import com.puhj.rye.bo.DepartmentTreeBO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author puhanjie
 * @since 2023-09-02
 */
@Tag(name = "部门接口", description = "部门操作相关接口")
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    private final UserService userService;

    private final RoleService roleService;

    private final DictionaryService dictionaryService;

    public DepartmentController(DepartmentService departmentService,
                                UserService userService,
                                RoleService roleService,
                                DictionaryService dictionaryService) {
        this.departmentService = departmentService;
        this.userService = userService;
        this.roleService = roleService;
        this.dictionaryService = dictionaryService;
    }

    @Operation(summary = "新增部门", description = "新增一个部门")
    @PostMapping
    @RequiresPermissions(Permissions.Department.ADD)
    public boolean add(@RequestBody DepartmentDTO departmentDTO) {
        return this.departmentService.add(departmentDTO);
    }

    @Operation(summary = "删除部门", description = "根据部门id数组删除岗位")
    @DeleteMapping
    @RequiresPermissions(Permissions.Department.DELETE)
    public boolean remove(@RequestBody List<Integer> ids) {
        return this.departmentService.removeByIds(ids);
    }

    @Operation(summary = "编辑部门", description = "编辑部门信息")
    @PutMapping
    @RequiresPermissions(Permissions.Department.EDIT)
    public boolean edit(@RequestBody DepartmentDTO departmentDTO) {
        return this.departmentService.edit(departmentDTO);
    }

    @Operation(summary = "查询部门列表", description = "分页查询部门列表")
    @Parameters({
            @Parameter(name = "code", description = "部门编码"),
            @Parameter(name = "name", description = "部门名称")
    })
    @GetMapping("/list")
    @RequiresPermissions(Permissions.Department.VIEW)
    public List<DepartmentDetailTreeVO> list(@RequestParam(value = "code", required = false) String code,
                                             @RequestParam(value = "name", required = false) String name) {
        return this.departmentService.list(code, name);
    }

    @Operation(summary = "获取选项数据", description = "获取部门表单选项及字典数据")
    @GetMapping("/options")
    @RequiresPermissions(Permissions.Department.VIEW)
    public DepartmentOptionsVO getDepartmentOptions() {
        List<DictionaryBO> deptStatus = this.dictionaryService.getItems("dept_status");
        List<UserBO> users = this.userService.getOptions();
        List<DepartmentTreeBO> departments = this.departmentService.getOptions();
        List<RoleBO> roles = this.roleService.getOptions();
        return new DepartmentOptionsVO(deptStatus, users, departments, roles);
    }

}
