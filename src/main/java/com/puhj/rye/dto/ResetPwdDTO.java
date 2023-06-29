package com.puhj.rye.dto;

import lombok.Data;

/**
 * @author puhanjie
 * @description 重置密码DTO
 * @create 2023/6/18 13:38
 * @modify 2023/6/18 13:38
 */
@Data
public class ResetPwdDTO {
    private Integer userId;

    private String newPwd;
}
