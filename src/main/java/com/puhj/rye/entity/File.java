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
@Schema(name = "File", description = "文件表")
@Data
@TableName("file")
public class File implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "文件id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "文件地址")
    @TableField("path")
    private String path;

    @Schema(description = "文件名")
    @TableField("name")
    private String name;

    @Schema(description = "文件大小(Byte)")
    @TableField("file_size")
    private Long fileSize;

    @Schema(description = "uuid")
    @TableField("uuid")
    private String uuid;

    @Schema(description = "上传者(用户id)")
    @TableField("upload_user")
    private Integer uploadUser;

    @Schema(description = "删除者(用户id)")
    @TableField("delete_user")
    private Integer deleteUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "上传时间")
    @TableField("upload_time")
    private LocalDateTime uploadTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "删除时间")
    @TableField("delete_time")
    private LocalDateTime deleteTime;

    public File(String path, String name, Long fileSize, String uuid, Integer uploadUser) {
        this.path = path;
        this.name = name;
        this.fileSize = fileSize;
        this.uuid = uuid;
        this.uploadUser = uploadUser;
    }

}
