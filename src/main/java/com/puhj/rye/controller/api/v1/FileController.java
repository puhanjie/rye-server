package com.puhj.rye.controller.api.v1;


import com.puhj.rye.service.FileService;
import com.puhj.rye.vo.FileVO;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

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
@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    @RequiresAuthentication
    public List<FileVO> upload(HttpServletRequest request) throws IOException {
        return this.fileService.upload(request);
    }

    @DeleteMapping("/remove")
    @RequiresAuthentication
    public void remove(@RequestParam String path, HttpServletRequest request) {
        this.fileService.remove(path, request);
    }

    @GetMapping("/download")
    @RequiresAuthentication
    public void download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.fileService.download(path, request, response);
    }

}

