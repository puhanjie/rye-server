package com.puhj.rye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.vo.RoleInfoVO;
import com.puhj.rye.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    int insertPermissionIdsByRoleId(@Param("roleId") Integer roleId,
                                    @Param("permissionIds") List<Integer> permissionIds);

    boolean deletePermissionIdsByRoleId(@Param("roleId") Integer roleId,
                                        @Param("permissionIds") List<Integer> permissionIds);

    List<Integer> selectPermissionIdsByRoleId(@Param("roleId") Integer roleId);

    Page<RoleInfoVO> list(Page<RoleInfoVO> page,
                          @Param("code") String code,
                          @Param("name") String name);

}
