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
 * 用户表
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Schema(name = "User对象", description = "用户表")
@Data
@TableName("user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户名，唯一")
    @TableField("username")
    private String username;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "删除时间")
    @TableField("delete_time")
    private Date deleteTime;

}
