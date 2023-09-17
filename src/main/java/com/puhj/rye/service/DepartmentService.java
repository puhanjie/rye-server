package com.puhj.rye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.puhj.rye.bo.DepartmentTreeBO;
import com.puhj.rye.dto.DepartmentDTO;
import com.puhj.rye.entity.Department;
import com.puhj.rye.vo.DepartmentDetailTreeVO;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author puhanjie
 * @since 2023-09-02
 */
public interface DepartmentService extends IService<Department> {

    boolean add(DepartmentDTO departmentDTO);

    boolean edit(DepartmentDTO departmentDTO);

    List<DepartmentDetailTreeVO> list(String code, String name);

    List<DepartmentTreeBO> getOptions();

}
