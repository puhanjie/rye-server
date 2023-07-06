package com.puhj.rye.dto;

import lombok.Data;

/**
 * @author puhanjie
 * @description 用户分页查询参数DTO
 * @create 2023-6-15
 */
@Data
public class UserPageDTO {
    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String username;

    private String phone;

    private String email;
}
