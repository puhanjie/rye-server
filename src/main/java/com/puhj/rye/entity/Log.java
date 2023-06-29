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
 * 操作日志表
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Data
@TableName("log")
@ApiModel(value = "Log对象", description = "操作日志表")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("响应状态码")
    @TableField("code")
    private Integer code;

    @ApiModelProperty("响应状态消息")
    @TableField("message")
    private String message;

    @ApiModelProperty("操作用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("操作用户")
    @TableField("username")
    private String username;

    @ApiModelProperty("接口地址")
    @TableField("path")
    private String path;

    @ApiModelProperty("操作时间")
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
