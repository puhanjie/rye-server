package com.puhj.rye.bo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 部门简要信息树数据对象
 * @create 2023-09-05
 */
@Schema(name = "DepartmentTreeBO", description = "部门简要信息树数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({ "id", "parentId", "code", "name", "children" })
public class DepartmentTreeBO extends TreeNodeBO {

    @Schema(description = "部门编码")
    private String code;

    @Schema(description = "部门名称")
    private String name;

}
