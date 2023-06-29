package com.puhj.rye.dto;

import lombok.Data;

/**
 * @author puhanjie
 * @description 角色分页查询参数
 * @create 2023/6/15 23:24
 * @modify 2023/6/15 23:24
 */
@Data
public class RolePageDTO {
    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String name;

    private String info;
}