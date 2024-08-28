package com.puhj.rye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.entity.File;
import com.puhj.rye.vo.FileInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 文件表 Mapper 接口
 * </p>
 *
 * @author puhanjie
 * @since 2022-12-14
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {

    void deleteByPath(@Param("path") String path, @Param("userId") Integer userId);

    Page<FileInfoVO> list(Page<FileInfoVO> page,
                          @Param("name") String name,
                          @Param("uploadUser") String uploadUser,
                          @Param("currentUserId") Integer currentUserId);

}
