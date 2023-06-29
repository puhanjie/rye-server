package com.puhj.rye.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@Data
@TableName("file")
@ApiModel(value = "File对象", description = "文件表")
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("文件地址")
    @TableField("path")
    private String path;

    @ApiModelProperty("文件名")
    @TableField("name")
    private String name;

    @ApiModelProperty("文件大小（Byte）")
    @TableField("file_size")
    private Long fileSize;

    @ApiModelProperty("文件唯一uuid值")
    @TableField("uuid")
    private String uuid;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("删除时间")
    @TableField("delete_time")
    private LocalDateTime deleteTime;

    public File(String path, String name, Long fileSize, String uuid) {
        this.path = path;
        this.name = name;
        this.fileSize = fileSize;
        this.uuid = uuid;
    }

}
