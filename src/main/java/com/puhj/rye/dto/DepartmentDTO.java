package com.puhj.rye.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author puhanjie
 * @description 部门参数对象
 * @create 2023-09-02
 */
@Schema(name = "DepartmentDTO", description = "部门参数对象")
@Data
public class DepartmentDTO {

    @Schema(description = "部门id")
    private Integer id;

    @Schema(description = "上级部门id")
    private Integer parentId;

    @Schema(description = "部门编码")
    private String code;

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "负责人")
    private Integer leader;

    @Schema(description = "部门状态")
    private String deptStatus;

    @Schema(description = "角色,id数组")
    private List<Integer> roles;

}
