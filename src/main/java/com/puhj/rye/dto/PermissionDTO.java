package com.puhj.rye.dto;

import com.puhj.rye.entity.Permission;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author puhanjie
 * @description 权限参数对象
 * @create 2023-8-26
 */
@Schema(name = "PermissionDTO", description = "权限参数对象")
@Data
public class PermissionDTO {

    @Schema(description = "权限id")
    private Integer id;

    @Schema(description = "权限编码")
    private String code;

    @Schema(description = "权限名称")
    private String name;

    @Schema(description = "菜单")
    private String menu;

    @Schema(description = "权限状态")
    private String permissionStatus;

    public Permission entity() {
        Permission permission = new Permission();
        permission.setId(this.id);
        permission.setCode(this.code);
        permission.setName(this.name);
        permission.setMenu(this.menu);
        permission.setPermissionStatus(this.permissionStatus);
        return permission;
    }

}
