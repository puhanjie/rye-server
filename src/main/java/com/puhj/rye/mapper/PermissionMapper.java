package com.puhj.rye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.entity.Permission;
import com.puhj.rye.vo.PermissionListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    Page<PermissionListVO> query(Page<PermissionListVO> page,
                                 @Param("name") String name,
                                 @Param("info") String info,
                                 @Param("menuName") String menuName);

}
