package com.puhj.rye.dto;

import com.puhj.rye.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author puhanjie
 * @description 角色参数对象
 * @create 2023-07-23
 */
@Schema(name = "RoleDTO", description = "角色参数对象")
@Data
public class RoleDTO {

    @Schema(description = "角色id")
    private Integer id;

    @Schema(description = "角色编码")
    private String code;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色状态")
    private String roleStatus;

    @Schema(description = "权限,id数组")
    private List<Integer> permissions;

    public Role entity() {
        Role role = new Role();
        role.setId(this.id);
        role.setCode(this.code);
        role.setName(this.name);
        role.setRoleStatus(this.roleStatus);
        return role;
    }

}
