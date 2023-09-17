package com.puhj.rye.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.puhj.rye.vo.DepartmentDetailTreeVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author puhanjie
 * @description 部门列表数据对象
 * @create 2023-09-02
 */
@Schema(name = "DepartmentInfoBO", description = "部门列表数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentInfoBO {

    @Schema(description = "部门id")
    private Integer id;

    @Schema(description = "上级部门")
    private DepartmentBO parentDept;

    @Schema(description = "部门编码")
    private String code;

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "负责人")
    private UserBO leader;

    @Schema(description = "部门状态")
    private DictionaryBO deptStatus;

    @Schema(description = "创建者")
    private UserBO createUser;

    @Schema(description = "更新者")
    private UserBO updateUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "角色")
    private List<RoleBO> roles;

    public DepartmentDetailTreeVO getTreeNode() {
        DepartmentDetailTreeVO departmentDetailTreeVO = new DepartmentDetailTreeVO();
        departmentDetailTreeVO.setId(this.getId());
        departmentDetailTreeVO.setParentId(this.getParentDept().getId());
        departmentDetailTreeVO.setParentDept(this.getParentDept());
        departmentDetailTreeVO.setCode(this.getCode());
        departmentDetailTreeVO.setName(this.getName());
        departmentDetailTreeVO.setLeader(this.getLeader());
        departmentDetailTreeVO.setDeptStatus(this.getDeptStatus());
        departmentDetailTreeVO.setCreateUser(this.getCreateUser());
        departmentDetailTreeVO.setUpdateUser(this.getUpdateUser());
        departmentDetailTreeVO.setCreateTime(this.getCreateTime());
        departmentDetailTreeVO.setUpdateTime(this.getUpdateTime());
        departmentDetailTreeVO.setRoles(this.getRoles());
        return departmentDetailTreeVO;
    }

}
