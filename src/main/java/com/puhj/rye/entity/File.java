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
 * 文件表
 * </p>
 *
 * @author puhanjie
 * @since 2022-12-14
 */
@Schema(name = "File对象", description = "文件表")
@Data
@TableName("file")
public class File implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "文件地址")
    @TableField("path")
    private String path;

    @Schema(description = "文件名")
    @TableField("name")
    private String name;

    @Schema(description = "文件大小（Byte）")
    @TableField("file_size")
    private Long fileSize;

    @Schema(description = "文件唯一uuid值")
    @TableField("uuid")
    private String uuid;

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

    public File(String path, String name, Long fileSize, String uuid) {
        this.path = path;
        this.name = name;
        this.fileSize = fileSize;
        this.uuid = uuid;
    }

}
