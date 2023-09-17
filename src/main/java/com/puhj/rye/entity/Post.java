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
 * 岗位表
 * </p>
 *
 * @author puhanjie
 * @since 2023-08-29
 */
@Schema(name = "Post", description = "岗位表")
@Data
@TableName("post")
public class Post implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "岗位id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "岗位编码")
    @TableField("code")
    private String code;

    @Schema(description = "岗位名称")
    @TableField("name")
    private String name;

    @Schema(description = "岗位状态")
    @TableField("post_status")
    private String postStatus;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;

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
