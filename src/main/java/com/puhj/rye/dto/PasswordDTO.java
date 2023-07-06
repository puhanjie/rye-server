package com.puhj.rye.dto;

import lombok.Data;

/**
 * @author puhanjie
 * @description 密码修改重置DTO
 * @create 2023-6-15
 */
@Data
public class PasswordDTO {
    // 默认为重置密码(1:重置密码;2:修改密码)
    private Integer type = 1;

    private Integer userId;

    private String currentPassword;

    private String newPassword;
}
