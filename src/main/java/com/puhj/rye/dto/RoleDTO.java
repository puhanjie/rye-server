package com.puhj.rye.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author puhanjie
 * @description 角色参数对象
 * @create 2023-07-23
 */
@Schema(name = "RoleDTO", description = "角色参数对象")
@Data
public class RoleDTO {

    @Schema(description = "角色id")
    private Integer id;

    @Schema(description = "角色名")
    private String name;

    @Schema(description = "角色信息")
    private String info;

    @Schema(description = "权限,id数组")
    private List<Integer> permissions;

}