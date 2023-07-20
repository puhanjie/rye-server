package com.puhj.rye.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author puhanjie
 * @description 密码修改重置参数
 * @create 2023-6-15
 */
@Schema(name = "PasswordDTO", description = "密码修改重置参数")
@Data
public class PasswordDTO {

    // 默认为重置密码(1:重置密码;2:修改密码)
    @Schema(description = "操作类型(1:重置密码;2:修改密码)")
    private Integer type = 1;

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "当前密码(重置密码时可不传)")
    private String currentPassword;

    @Schema(description = "新密码")
    private String newPassword;

}
