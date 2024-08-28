package com.puhj.rye.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.puhj.rye.bo.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author puhanjie
 * @description 部门树数据对象
 * @create 2023-09-03
 */
@Schema(name = "DepartmentDetailTreeVO", description = "部门树数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id", "parentId", "parentDept", "code", "name", "leader", "deptStatus",
        "createUser", "updateUser", "createTime", "updateTime", "roles", "children"})
public class DepartmentDetailTreeVO extends TreeNodeBO {

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

}
