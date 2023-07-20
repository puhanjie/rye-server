package com.puhj.rye.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author puhanjie
 * @description 权限列表数据对象
 * @create 2023-6-15
 */
@Schema(name = "PermissionListVO", description = "权限列表数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionListVO {

    @Schema(description = "权限id")
    private Integer id;

    @Schema(description = "权限名")
    private String name;

    @Schema(description = "权限信息")
    private String info;

    @Schema(description = "隶属菜单")
    private String menu;

    @Schema(description = "隶属菜单名称")
    private String menuName;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
