package com.puhj.rye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.puhj.rye.entity.Department;
import com.puhj.rye.bo.DepartmentInfoBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author puhanjie
 * @since 2023-09-02
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

    int insertRoleIdsByDepartmentId(@Param("departmentId") Integer departmentId, @Param("roleIds") List<Integer> roleIds);

    boolean deleteRoleIdsByDepartmentId(@Param("departmentId") Integer departmentId, @Param("roleIds") List<Integer> roleIds);

    List<Integer> selectRoleIdsByDepartmentId(@Param("departmentId") Integer departmentId);

    List<DepartmentInfoBO> list(@Param("code") String code, @Param("name") String name);

}
