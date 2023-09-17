package com.puhj.rye.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 权限简要信息数据对象
 * @create 2023-6-14
 */
@Schema(name = "PermissionBO", description = "权限简要信息数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionBO {

    @Schema(description = "权限id")
    private Integer id;

    @Schema(description = "权限编码")
    private String code;

    @Schema(description = "权限名称")
    private String name;

    @Schema(description = "菜单")
    private String menu;

}
