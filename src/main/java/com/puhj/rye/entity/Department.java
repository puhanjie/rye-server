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
 * 部门表
 * </p>
 *
 * @author puhanjie
 * @since 2023-09-02
 */
@Schema(name = "Department", description = "部门表")
@Data
@TableName("department")
public class Department implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "部门id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "上级部门id")
    @TableField("parent_id")
    private Integer parentId;

    @Schema(description = "部门编码")
    @TableField("code")
    private String code;

    @Schema(description = "部门名称")
    @TableField("name")
    private String name;

    @Schema(description = "负责人(用户id)")
    @TableField("leader")
    private Integer leader;

    @Schema(description = "部门状态(字典:dept_status)")
    @TableField("dept_status")
    private String deptStatus;

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
