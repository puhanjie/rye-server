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
 * 用户角色表
 * </p>
 *
 * @author puhanjie
 * @since 2023-07-22
 */
@Schema(name = "UserRole", description = "用户角色表")
@Data
@TableName("user_role")
public class UserRole implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户id")
    @TableField("user_id")
    private Integer userId;

    @Schema(description = "角色id")
    @TableField("role_id")
    private Integer roleId;

}
