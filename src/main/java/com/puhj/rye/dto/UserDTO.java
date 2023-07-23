package com.puhj.rye.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author puhanjie
 * @description 用户参数对象
 * @create 2023-07-22
 */
@Schema(name = "UserDTO", description = "用户参数对象")
@Data
public class UserDTO {

    @Schema(description = "用户id")
    private Integer id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "手机")
    private String phone;

    @Schema(description = "头像url")
    private String avatar;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "角色,id数组")
    private List<Integer> roles;

}
