package com.puhj.rye.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description Permission简要Vo
 * @create 2023/6/14 22:12
 * @modify 2023/6/14 22:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionSimpleVO {
    private Integer id;

    private String name;

    private String info;
}
