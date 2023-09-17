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
 * 字典表
 * </p>
 *
 * @author puhanjie
 * @since 2023-07-27
 */
@Schema(name = "Dictionary", description = "字典表")
@Data
@TableName("dictionary")
public class Dictionary implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "字典类型")
    @TableField("dict_type")
    private String dictType;

    @Schema(description = "字典名称")
    @TableField("dict_name")
    private String dictName;

    @Schema(description = "字典键值")
    @TableField("dict_value")
    private String dictValue;

    @Schema(description = "字典标签")
    @TableField("dict_label")
    private String dictLabel;

    @Schema(description = "描述")
    @TableField("description")
    private String description;

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
