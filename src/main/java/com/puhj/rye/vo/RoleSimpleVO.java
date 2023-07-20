package com.puhj.rye.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 角色简要信息数据对象
 * @create 2023-6-14
 */
@Schema(name = "RoleSimpleVO", description = "角色简要信息数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleSimpleVO {

    @Schema(description = "角色id")
    private Integer id;

    @Schema(description = "角色名")
    private String name;

    @Schema(description = "角色信息")
    private String info;

}
