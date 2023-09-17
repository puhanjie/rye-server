package com.puhj.rye.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 角色简要信息数据对象
 * @create 2023-6-14
 */
@Schema(name = "RoleBO", description = "角色简要信息数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleBO {

    @Schema(description = "角色id")
    private Integer id;

    @Schema(description = "角色编码")
    private String code;

    @Schema(description = "角色名称")
    private String name;

}
