package com.puhj.rye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.puhj.rye.entity.File;
import com.puhj.rye.vo.FileVO;

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

    List<FileVO> upload(HttpServletRequest request) throws IOException;

    void remove(String path, HttpServletRequest request);

    void download(String path, HttpServletRequest request, HttpServletResponse response) throws IOException;

}
