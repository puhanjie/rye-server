package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.common.utils.DateUtil;
import com.puhj.rye.common.utils.PathUtil;
import com.puhj.rye.common.utils.SubjectUtil;
import com.puhj.rye.entity.File;
import com.puhj.rye.mapper.FileMapper;
import com.puhj.rye.service.FileService;
import com.puhj.rye.vo.FileInfoVO;
import com.puhj.rye.vo.FileVO;
import com.puhj.rye.vo.PageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

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

    @Transactional
    @Override
    public List<FileVO> upload(MultipartFile[] files, HttpServletRequest request) throws IOException {
        Integer currentUserId = SubjectUtil.getSubjectId();
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
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            Long fileSize = file.getSize();
            String uuid = UUID.randomUUID().toString();
            String saveName = uuid + Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));

            file.transferTo(new java.io.File(folder, saveName));

            String filePath = PathUtil.getBasePath(request) + "/" + this.path + "/" + dateFormat + "/" + saveName;
            fileList.add(new FileVO(filePath, fileName));

            File fileData = new File();
            fileData.setPath(filePath);
            fileData.setName(fileName);
            fileData.setFileSize(fileSize);
            fileData.setUuid(uuid);
            fileData.setUploadUser(currentUserId);
            this.fileMapper.insert(fileData);
        }

        return fileList;
    }

    @Transactional
    @Override
    public boolean remove(String path, HttpServletRequest request) {
        Integer currentUserId = SubjectUtil.getSubjectId();
        String fileDir = System.getProperty("user.dir") + path.substring(PathUtil.getBasePath(request).length());
        this.fileMapper.deleteByPath(path, currentUserId); // 删除了的文件,更新文件表的delete_time字段表示已删除
        FileSystemUtils.deleteRecursively(new java.io.File(fileDir)); // 移除本地文件
        return true;
    }

    @Override
    public void download(String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileDir = System.getProperty("user.dir") + path.substring(PathUtil.getBasePath(request).length());
        java.io.File folder = new java.io.File(fileDir);
        if (!folder.exists()) {
            throw new IOException("文件不存在");
        }

        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("path", path).isNull("delete_time");
        String fileName = this.fileMapper.selectOne(queryWrapper).getName();
        byte[] buffer = new byte[1024];

        try (FileInputStream fis = new FileInputStream(folder);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            OutputStream os = response.getOutputStream();
            response.setContentType("application/octet-stream");
            // attachment表示以附件形式下载,若为inline则表示在线方式打开;fileName为下载后的文件名
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
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

    @Override
    public PageVO<FileInfoVO> list(Page<FileInfoVO> page, String name, String uploadUser) {
        Page<FileInfoVO> pageList = this.fileMapper.list(page, name, uploadUser, SubjectUtil.getSubjectId());
        return new PageVO<>(pageList);
    }

}
