package com.puhj.rye.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 密码修改重置BO
 * @create 2023-6-18
 */
@Schema(name = "PasswordBO", description = "密码修改重置BO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordBO {

    @Schema(description = "用户id")
    private Integer userId;

    @Schema(description = "新密码")
    private String newPassword;

}
