package com.puhj.rye.controller.api.v1;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.service.FileService;
import com.puhj.rye.vo.FileInfoVO;
import com.puhj.rye.vo.FileVO;
import com.puhj.rye.vo.PageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 文件表 前端控制器
 * </p>
 *
 * @author puhanjie
 * @since 2022-12-14
 */
@Tag(name = "文件接口", description = "文件操作相关接口")
@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @Operation(summary = "文件上传", description = "上传一个或多个文件")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {@Content(
            mediaType = "multipart/form-data",
            schema = @Schema(type = "object"),
            schemaProperties = {
                    @SchemaProperty(
                            name = "files",
                            schema = @Schema(type = "string", format = "binary")
                    )
            }
    )})
    @PostMapping
    @RequiresPermissions(Permissions.File.UPLOAD)
    public List<FileVO> upload(@RequestBody MultipartFile[] files, HttpServletRequest request) throws IOException {
        return this.fileService.upload(files, request);
    }

    @Operation(summary = "删除文件", description = "根据文件路径删除一个文件")
    @DeleteMapping
    @RequiresPermissions(Permissions.File.DELETE)
    public boolean remove(@RequestBody String path, HttpServletRequest request) {
        return this.fileService.remove(path, request);
    }

    @Operation(summary = "文件下载", description = "根据文件路径下载一个文件")
    @GetMapping
    @RequiresPermissions(Permissions.File.DOWNLOAD)
    public void download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.fileService.download(path, request, response);
    }

    @Operation(summary = "查询文件列表", description = "分页查询文件列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "分页查询页码"),
            @Parameter(name = "pageSize", description = "每页数据大小"),
            @Parameter(name = "name", description = "文件名"),
            @Parameter(name = "uploadUser", description = "上传者")
    })
    @GetMapping("/list")
    @RequiresPermissions(Permissions.File.VIEW)
    public PageVO<FileInfoVO> list(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                   @RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "uploadUser", required = false) String uploadUser) {
        Page<FileInfoVO> page = new Page<>(pageNum, pageSize);
        return this.fileService.list(page, name, uploadUser);
    }

}
