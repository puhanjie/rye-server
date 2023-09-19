package com.puhj.rye.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puhj.rye.entity.File;
import com.puhj.rye.vo.FileInfoVO;
import com.puhj.rye.vo.FileVO;
import com.puhj.rye.vo.PageVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author puhanjie
 * @since 2022-12-14
 */
public interface FileService extends IService<File> {

    List<FileVO> upload(MultipartFile[] files, HttpServletRequest request) throws IOException;

    boolean remove(String path, HttpServletRequest request);

    void download(String path, HttpServletRequest request, HttpServletResponse response) throws IOException;

    PageVO<FileInfoVO> list(Page<FileInfoVO> page, String name, String uploadUser);

}
