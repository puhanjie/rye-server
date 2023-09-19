package com.puhj.rye.controller.api.v1;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.bo.DictionaryBO;
import com.puhj.rye.bo.RoleBO;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.dto.PostDTO;
import com.puhj.rye.service.DictionaryService;
import com.puhj.rye.service.PostService;
import com.puhj.rye.service.RoleService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.PostInfoVO;
import com.puhj.rye.vo.PostOptionsVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 岗位表 前端控制器
 * </p>
 *
 * @author puhanjie
 * @since 2023-08-29
 */
@Tag(name = "岗位接口", description = "岗位操作相关接口")
@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    private final RoleService roleService;

    private final DictionaryService dictionaryService;

    public PostController(PostService postService,
                          RoleService roleService,
                          DictionaryService dictionaryService) {
        this.postService = postService;
        this.roleService = roleService;
        this.dictionaryService = dictionaryService;
    }

    @Operation(summary = "新增岗位", description = "新增一个岗位")
    @PostMapping
    @RequiresPermissions(Permissions.Post.ADD)
    public boolean add(@RequestBody PostDTO postDTO) {
        return this.postService.add(postDTO);
    }

    @Operation(summary = "删除岗位", description = "根据岗位id数组删除岗位")
    @DeleteMapping
    @RequiresPermissions(Permissions.Post.DELETE)
    public boolean remove(@RequestBody List<Integer> ids) {
        return this.postService.removeByIds(ids);
    }

    @Operation(summary = "编辑岗位", description = "编辑岗位信息")
    @PutMapping
    @RequiresPermissions(Permissions.Post.EDIT)
    public boolean edit(@RequestBody PostDTO postDTO) {
        return this.postService.edit(postDTO);
    }

    @Operation(summary = "查询岗位列表", description = "分页查询岗位列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "分页查询页码"),
            @Parameter(name = "pageSize", description = "每页数据大小"),
            @Parameter(name = "code", description = "岗位编码"),
            @Parameter(name = "name", description = "岗位名称")
    })
    @GetMapping("/list")
    @RequiresPermissions(Permissions.Post.VIEW)
    public PageVO<PostInfoVO> list(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                   @RequestParam(value = "code", required = false) String code,
                                   @RequestParam(value = "name", required = false) String name) {
        Page<PostInfoVO> page = new Page<>(pageNum, pageSize);
        return this.postService.list(page, code, name);
    }

    @Operation(summary = "获取选项数据", description = "获取岗位表单选项及字典数据")
    @GetMapping("/options")
    @RequiresPermissions(Permissions.Post.VIEW)
    public PostOptionsVO getPostOptions() {
        List<DictionaryBO> postStatus = this.dictionaryService.getItems("post_status");
        List<RoleBO> roles = this.roleService.getOptions();
        return new PostOptionsVO(postStatus, roles);
    }

}

