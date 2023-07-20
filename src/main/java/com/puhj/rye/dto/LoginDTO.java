package com.puhj.rye.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author puhanjie
 * @description 用户登录参数对象
 * @create 2022-11-30
 */
@Schema(name = "LoginDTO", description = "用户登录参数对象")
@Data
public class LoginDTO {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

}
