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
 * @description 用户列表数据对象
 * @create 2023-6-15
 */
@Schema(name = "UserListVO", description = "用户列表数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListVO {

    @Schema(description = "用户id")
    private Integer id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "手机")
    private String phone;

    @Schema(description = "头像url")
    private String avatar;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Schema(description = "角色")
    private List<RoleSimpleVO> roles;

}
