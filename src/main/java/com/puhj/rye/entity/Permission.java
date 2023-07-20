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
import java.util.Date;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Schema(name = "Permission对象", description = "权限表")
@Data
@TableName("permission")
public class Permission implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "权限名称")
    @TableField("name")
    private String name;

    @Schema(description = "权限信息")
    @TableField("info")
    private String info;

    @Schema(description = "归属菜单")
    @TableField("menu")
    private String menu;

    @Schema(description = "归属菜单名称")
    @TableField("menu_name")
    private String menuName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    @TableField("update_time")
    private Date updateTime;

}
