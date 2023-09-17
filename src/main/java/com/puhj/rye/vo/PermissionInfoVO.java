package com.puhj.rye.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.puhj.rye.bo.DictionaryBO;
import com.puhj.rye.bo.UserBO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author puhanjie
 * @description 权限列表数据对象
 * @create 2023-6-15
 */
@Schema(name = "PermissionInfoVO", description = "权限列表数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionInfoVO {

    @Schema(description = "权限id")
    private Integer id;

    @Schema(description = "权限编码")
    private String code;

    @Schema(description = "权限名称")
    private String name;

    @Schema(description = "菜单")
    private String menu;

    @Schema(description = "权限状态")
    private DictionaryBO permissionStatus;

    @Schema(description = "创建者")
    private UserBO createUser;

    @Schema(description = "更新者")
    private UserBO updateUser;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
