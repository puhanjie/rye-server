package com.puhj.rye.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 密码修改重置BO
 * @create 2023-6-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordBO {
    private Integer userId;

    private String newPassword;
}
