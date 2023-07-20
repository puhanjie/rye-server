package com.puhj.rye.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 权限简要信息数据对象
 * @create 2023-6-14
 */
@Schema(name = "PermissionSimpleVO", description = "权限简要信息数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionSimpleVO {

    @Schema(description = "权限id")
    private Integer id;

    @Schema(description = "权限名")
    private String name;

    @Schema(description = "权限信息")
    private String info;

}
