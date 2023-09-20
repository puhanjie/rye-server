package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.vo.DepartmentDetailTreeVO;
import com.puhj.rye.bo.DepartmentInfoBO;
import com.puhj.rye.bo.DepartmentTreeBO;
import com.puhj.rye.common.constant.ResultCode;
import com.puhj.rye.common.exception.HttpException;
import com.puhj.rye.common.utils.ContrastUtil;
import com.puhj.rye.common.utils.SubjectUtil;
import com.puhj.rye.common.utils.TreeUtil;
import com.puhj.rye.dto.DepartmentDTO;
import com.puhj.rye.entity.Department;
import com.puhj.rye.mapper.DepartmentMapper;
import com.puhj.rye.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author puhanjie
 * @since 2023-09-02
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }


    @Override
    public boolean add(DepartmentDTO departmentDTO) {
        Integer currentUserId = SubjectUtil.getSubjectId();
        Department department = new Department();
        department.setParentId(departmentDTO.getParentId());
        department.setCode(departmentDTO.getCode());
        department.setName(departmentDTO.getName());
        department.setLeader(departmentDTO.getLeader());
        department.setDeptStatus(departmentDTO.getDeptStatus());
        department.setCreateUser(currentUserId);
        department.setUpdateUser(currentUserId);

        // 新增部门
        if (this.departmentMapper.insert(department) <= 0) {
            throw new HttpException(ResultCode.DEPARTMENT_ADD_ERROR);
        }

        // 分配角色
        if (departmentDTO.getRoles() != null && !departmentDTO.getRoles().isEmpty()) {
            if (this.departmentMapper.insertRoleIdsByDepartmentId(department.getId(), departmentDTO.getRoles()) <= 0) {
                throw new HttpException(ResultCode.DEPARTMENT_SET_ROLES_ERROR);
            }
        }

        return true;
    }

    @Override
    public boolean edit(DepartmentDTO departmentDTO) {
        Department department = this.departmentMapper.selectById(departmentDTO.getId());
        UpdateWrapper<Department> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", departmentDTO.getId())
                .set("parent_id", departmentDTO.getParentId())
                .set("code", departmentDTO.getCode())
                .set("name", departmentDTO.getName())
                .set("leader", departmentDTO.getLeader())
                .set("dept_status", departmentDTO.getDeptStatus())
                .set("update_user", SubjectUtil.getSubjectId());

        // 编辑部门
        if (this.departmentMapper.update(department, updateWrapper) <= 0) {
            throw new HttpException(ResultCode.DEPARTMENT_EDIT_ERROR);
        }

        // 分配角色
        List<Integer> roleIds = this.departmentMapper.selectRoleIdsByDepartmentId(departmentDTO.getId());
        Map<String, List<Integer>> roleMap = ContrastUtil.getContrast(roleIds, departmentDTO.getRoles());
        if (!roleMap.get("remove").isEmpty()) {
            // 清除被移除的角色
            if (!this.departmentMapper.deleteRoleIdsByDepartmentId(departmentDTO.getId(), roleMap.get("remove"))) {
                throw new HttpException(ResultCode.DEPARTMENT_SET_ROLES_ERROR);
            }
        }
        if (!roleMap.get("add").isEmpty()) {
            // 添加新分配的角色
            if (this.departmentMapper.insertRoleIdsByDepartmentId(departmentDTO.getId(), roleMap.get("add")) <= 0) {
                throw new HttpException(ResultCode.DEPARTMENT_SET_ROLES_ERROR);
            }
        }

        return true;
    }

    @Override
    public List<DepartmentDetailTreeVO> list(String code, String name) {
        List<DepartmentInfoBO> departments = this.departmentMapper.list(code, name);

        // 转为树节点数据
        List<DepartmentDetailTreeVO> departmentTreeList = new ArrayList<>();
        for (DepartmentInfoBO departmentInfoBO : departments) {
            departmentTreeList.add(departmentInfoBO.getTreeNode());
        }

        // 返回树结构数据
        return TreeUtil.build(departmentTreeList);
    }

    @Override
    public List<DepartmentTreeBO> getOptions() {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_status", "0").isNull("delete_time");
        List<Department> departments = this.departmentMapper.selectList(queryWrapper);

        // 转为树节点数据
        List<DepartmentTreeBO> departmentTreeList = new ArrayList<>();
        for (Department department : departments) {
            DepartmentTreeBO departmentTreeBO = new DepartmentTreeBO();
            departmentTreeBO.setId(department.getId());
            departmentTreeBO.setParentId(department.getParentId());
            departmentTreeBO.setCode(department.getCode());
            departmentTreeBO.setName(department.getName());
            departmentTreeList.add(departmentTreeBO);
        }

        // 返回树结构数据
        return TreeUtil.build(departmentTreeList);
    }

}