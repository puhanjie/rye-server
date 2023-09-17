package com.puhj.rye.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.puhj.rye.bo.UserBO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author puhanjie
 * @description
 * @create 2023-08-29
 */
@Schema(name = "LogInfoVO", description = "角色简要信息数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInfoVO {

    @Schema(description = "日志id")
    private Integer id;

    @Schema(description = "请求url")
    private String url;

    @Schema(description = "响应状态码")
    private Integer code;

    @Schema(description = "响应消息")
    private String message;

    @Schema(description = "操作人")
    private UserBO operateUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "操作时间")
    private LocalDateTime operateTime;

}
