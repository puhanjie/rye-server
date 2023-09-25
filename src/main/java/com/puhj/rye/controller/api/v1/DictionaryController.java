package com.puhj.rye.controller.api.v1;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.bo.DictionaryBO;
import com.puhj.rye.vo.DictionaryInfoVO;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.dto.DictionaryDTO;
import com.puhj.rye.service.DictionaryService;
import com.puhj.rye.vo.PageVO;
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
 * 字典表 前端控制器
 * </p>
 *
 * @author puhanjie
 * @since 2023-07-27
 */
@Tag(name = "字典接口", description = "字典操作相关接口")
@RestController
@RequestMapping("/api/v1/dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Operation(summary = "新增字典", description = "增加一个字典项")
    @PostMapping
    @RequiresPermissions(Permissions.Dictionary.ADD)
    public boolean add(@RequestBody DictionaryDTO dictionaryDTO) {
        return this.dictionaryService.add(dictionaryDTO);
    }

    @Operation(summary = "删除字典", description = "根据字典id数组删除字典")
    @DeleteMapping
    @RequiresPermissions(Permissions.Dictionary.DELETE)
    public boolean remove(@RequestBody List<Integer> ids) {
        return this.dictionaryService.removeByIds(ids);
    }

    @Operation(summary = "编辑字典", description = "编辑字典信息")
    @PutMapping
    @RequiresPermissions(Permissions.Dictionary.EDIT)
    public boolean edit(@RequestBody DictionaryDTO dictionaryDTO) {
        return this.dictionaryService.edit(dictionaryDTO);
    }

    @Operation(summary = "查询字典列表", description = "分页查询字典列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "分页查询页码"),
            @Parameter(name = "pageSize", description = "每页数据大小"),
            @Parameter(name = "dictType", description = "字典类型"),
            @Parameter(name = "dictLabel", description = "字典标签")
    })
    @GetMapping("/list")
    @RequiresPermissions(Permissions.Dictionary.VIEW)
    public PageVO<DictionaryInfoVO> list(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                         @RequestParam(value = "dictType", required = false) String dictType,
                                         @RequestParam(value = "dictLabel", required = false) String dictLabel) {
        Page<DictionaryInfoVO> page = new Page<>(pageNum, pageSize);
        return this.dictionaryService.list(page, dictType, dictLabel);
    }

    @Operation(summary = "查询字典项", description = "根据字典类型查询字典项")
    @Parameters({
            @Parameter(name = "dictType", description = "字典类型")
    })
    @GetMapping("/items")
    @RequiresPermissions(value = {Permissions.Dictionary.VIEW, Permissions.User.VIEW, Permissions.Settings.VIEW}, logical = Logical.OR)
    public List<DictionaryBO> getItems(@RequestParam String dictType) {
        return this.dictionaryService.getItems(dictType);
    }

}
