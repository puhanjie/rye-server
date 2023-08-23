package com.puhj.rye.controller.api.v1;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.entity.Dictionary;
import com.puhj.rye.service.DictionaryService;
import com.puhj.rye.vo.DictionaryListVO;
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
    public boolean add(@RequestBody Dictionary dictionary) {
        return this.dictionaryService.save(dictionary);
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
    public boolean edit(@RequestBody Dictionary dictionary) {
        return this.dictionaryService.updateById(dictionary);
    }

    @Operation(summary = "查询字典列表", description = "分页查询字典列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "分页查询页码"),
            @Parameter(name = "pageSize", description = "每页数据大小"),
            @Parameter(name = "dictName", description = "字典名"),
            @Parameter(name = "itemText", description = "字典值文本")
    })
    @GetMapping("/list")
    @RequiresPermissions(Permissions.Dictionary.VIEW)
    public PageVO<DictionaryListVO> list(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                         @RequestParam(value = "dictName", required = false) String dictName,
                                         @RequestParam(value = "itemText", required = false) String itemText) {
        Page<DictionaryListVO> page = new Page<>(pageNum, pageSize);
        return this.dictionaryService.list(page, dictName, itemText);
    }

    @Operation(summary = "查询字典项", description = "根据字典类型查询字典项")
    @Parameters({
            @Parameter(name = "dictName", description = "字典名")
    })
    @GetMapping("/items")
    @RequiresPermissions(value = {Permissions.Dictionary.VIEW, Permissions.User.VIEW}, logical = Logical.OR)
    public List<Dictionary> getItems(@RequestParam String dictName) {
        return this.dictionaryService.getItems(dictName);
    }

}
