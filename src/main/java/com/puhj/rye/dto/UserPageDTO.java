package com.puhj.rye.dto;

import lombok.Data;

/**
 * @author puhanjie
 * @description 用户分页查询参数
 * @create 2023/6/15 22:14
 * @modify 2023/6/15 22:14
 */
@Data
public class UserPageDTO {
    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String username;

    private String phone;

    private String email;
}
