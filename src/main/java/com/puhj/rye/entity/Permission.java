package com.puhj.rye.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Schema(name = "Permission", description = "权限表")
@Data
@TableName("permission")
public class Permission implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "权限编码")
    @TableField("code")
    private String code;

    @Schema(description = "权限名称")
    @TableField("name")
    private String name;

    @Schema(description = "菜单")
    @TableField("menu")
    private String menu;

    @Schema(description = "权限状态")
    @TableField("permission_status")
    private String permissionStatus;

    @Schema(description = "创建者(用户id)")
    @TableField("create_user")
    private Integer createUser;

    @Schema(description = "更新者(用户id)")
    @TableField("update_user")
    private Integer updateUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "删除时间")
    @TableField("delete_time")
    private LocalDateTime deleteTime;

}
