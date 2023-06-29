package com.puhj.rye.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description Role简要Vo
 * @create 2023/6/14 21:47
 * @modify 2023/6/14 21:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleSimpleVO {
    private Integer id;

    private String name;

    private String info;
}
