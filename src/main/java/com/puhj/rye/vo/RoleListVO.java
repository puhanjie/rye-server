package com.puhj.rye.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author puhanjie
 * @description 角色列表数据对象
 * @create 2023-6-15
 */
@Schema(name = "RoleListVO", description = "角色列表数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleListVO {

    @Schema(description = "角色id")
    private Integer id;

    @Schema(description = "角色名")
    private String name;

    @Schema(description = "角色信息")
    private String info;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Schema(description = "对应权限")
    private List<PermissionSimpleVO> permissions;

}
