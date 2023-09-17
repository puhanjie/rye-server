package com.puhj.rye.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 部门简要信息数据对象
 * @create 2023-09-02
 */
@Schema(name = "DepartmentBO", description = "部门简要信息数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentBO {

    @Schema(description = "部门id")
    private Integer id;

    @Schema(description = "部门编码")
    private String code;

    @Schema(description = "部门名称")
    private String name;

}
