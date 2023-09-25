package com.puhj.rye.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Schema(name = "User", description = "用户表")
@Data
@TableName("user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "部门(部门id)")
    @TableField("department")
    private Integer department;

    @Schema(description = "用户名")
    @TableField("username")
    private String username;

    @Schema(description = "姓名")
    @TableField("name")
    private String name;

    @Schema(description = "性别(字典:sex)")
    @TableField("sex")
    private String sex;

    @Schema(description = "用户状态(字典:user_status)")
    @TableField("user_status")
    private String userStatus;

    @Schema(description = "登陆密码")
    @TableField("password")
    private String password;

    @Schema(description = "用户手机")
    @TableField("phone")
    private String phone;

    @Schema(description = "头像url")
    @TableField("avatar")
    private String avatar;

    @Schema(description = "邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "创建者(用户id)")
    @TableField(value = "create_user")
    private Integer createUser;

    @Schema(description = "更新者(用户id)")
    @TableField(value = "update_user")
    private Integer updateUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "删除时间")
    @TableField("delete_time")
    private LocalDateTime deleteTime;

}
