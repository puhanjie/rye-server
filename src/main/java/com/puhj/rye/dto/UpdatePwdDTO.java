package com.puhj.rye.dto;

import lombok.Data;

/**
 * @author puhanjie
 * @description 修改密码DTO
 * @create 2023/6/18 12:18
 * @modify 2023/6/18 12:18
 */
@Data
public class UpdatePwdDTO {
    private Integer userId;

    private String currPwd;

    private String newPwd;
}
