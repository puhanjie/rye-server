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
 * 操作日志表
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Schema(name = "Log", description = "操作日志表")
@Data
@TableName("log")
public class Log implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "响应状态码")
    @TableField("code")
    private Integer code;

    @Schema(description = "响应状态消息")
    @TableField("message")
    private String message;

    @Schema(description = "操作用户ID")
    @TableField("user_id")
    private Integer userId;

    @Schema(description = "操作用户")
    @TableField("username")
    private String username;

    @Schema(description = "接口地址")
    @TableField("path")
    private String path;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "操作时间")
    @TableField("operate_time")
    private LocalDateTime operateTime;

    public Log(Integer code, String message, Integer userId, String username, String path) {
        this.code = code;
        this.message = message;
        this.userId = userId;
        this.username = username;
        this.path = path;
    }

}
