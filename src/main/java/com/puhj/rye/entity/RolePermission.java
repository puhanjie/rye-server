package com.puhj.rye.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author puhanjie
 * @since 2023-07-22
 */
@Schema(name = "RolePermission", description = "角色权限表")
@Data
@TableName("role_permission")
public class RolePermission implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "角色id")
    @TableField("role_id")
    private Integer roleId;

    @Schema(description = "权限id")
    @TableField("permission_id")
    private Integer permissionId;

}
