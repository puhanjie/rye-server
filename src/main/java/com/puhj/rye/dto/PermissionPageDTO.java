package com.puhj.rye.dto;

import lombok.Data;

/**
 * @author puhanjie
 * @description 权限分页查询参数
 * @create 2023/6/15 23:55
 * @modify 2023/6/15 23:55
 */
@Data
public class PermissionPageDTO {
    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String name;

    private String info;

    private String menu;

    private String menuName;
}
