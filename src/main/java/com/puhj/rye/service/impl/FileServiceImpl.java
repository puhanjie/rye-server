package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.common.utils.BasePathUtil;
import com.puhj.rye.common.utils.DateUtil;
import com.puhj.rye.entity.File;
import com.puhj.rye.mapper.FileMapper;
import com.puhj.rye.service.FileService;
import com.puhj.rye.vo.FileVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author puhanjie
 * @since 2022-12-14
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    private final FileMapper fileMapper;
    @Value("${files.path}")
    private String path;

    public FileServiceImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public List<FileVO> upload(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> files = multipartRequest.getFileMap();

        // 创建目录
        String fileDir = System.getProperty("user.dir");
        String dateFormat = DateUtil.getDateFormat(new Date(), "yyyyMMdd");
        java.io.File folder = new java.io.File(fileDir + "/" + this.path + "/" + dateFormat);
        if (!folder.exists()) {
            boolean flag = folder.mkdirs();
            if (!flag) {
                throw new IOException("文件目录创建失败");
            }
        }

        List<FileVO> fileList = new ArrayList<>();
        for (Map.Entry<String, MultipartFile> entry : files.entrySet()) {
            String fileName = entry.getValue().getOriginalFilename();
            Long fileSize = entry.getValue().getSize();
            String uuid = UUID.randomUUID().toString();
            String saveName = uuid + Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));

            entry.getValue().transferTo(new java.io.File(folder, saveName));

            String filePath = BasePathUtil.getBasePath(request) + "/" + this.path + "/" + dateFormat + "/" + saveName;
            fileList.add(new FileVO(fileName, uuid, filePath));

            this.fileMapper.insert(new File(filePath, fileName, fileSize, uuid));
        }

        return fileList;
    }

    @Override
    public void remove(String path, HttpServletRequest request) {
        String fileDir = System.getProperty("user.dir") + path.substring(BasePathUtil.getBasePath(request).length());
        this.fileMapper.deleteByPath(path); // 删除了的文件，更新文件表的delete_time字段表示已删除
        FileSystemUtils.deleteRecursively(new java.io.File(fileDir));
    }

    @Override
    public void download(String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileDir = System.getProperty("user.dir") + path.substring(BasePathUtil.getBasePath(request).length());
        java.io.File folder = new java.io.File(fileDir);
        if (!folder.exists()) {
            throw new IOException("文件不存在");
        }

        String fileName = this.fileMapper.selectByPath(path).getName();
        byte[] buffer = new byte[1024];

        try (FileInputStream fis = new FileInputStream(folder);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            OutputStream os = response.getOutputStream();
            response.setContentType("application/octet-stream");
            // attachment表示以附件形式下载，若为inline则表示在线方式打开；fileName为下载后的文件名
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            response.setHeader("Content-Length", String.valueOf(folder.length()));
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer);
                i = bis.read(buffer);
            }
        } catch (IOException e) {
            throw new IOException("文件下载失败");
        }
    }

}
