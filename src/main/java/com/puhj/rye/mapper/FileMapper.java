package com.puhj.rye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.puhj.rye.entity.File;
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

}
