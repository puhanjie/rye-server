package com.puhj.rye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.entity.Role;
import com.puhj.rye.vo.RoleListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    Page<RoleListVO> query(Page<RoleListVO> page,
                           @Param("name") String name,
                           @Param("info") String info);

}
