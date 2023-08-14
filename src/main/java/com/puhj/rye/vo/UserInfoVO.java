package com.puhj.rye.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.puhj.rye.entity.Permission;
import com.puhj.rye.entity.Role;
import com.puhj.rye.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author puhanjie
 * @description 用户基本信息数据对象
 * @create 2022-3-21
 */
@Schema(name = "UserInfoVO", description = "用户基本信息数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO {

    @Schema(description = "用户id")
    private Integer id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "用户状态")
    private String userStatus;

    @Schema(description = "手机")
    private String phone;

    @Schema(description = "头像url")
    private String avatar;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "角色")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RoleSimpleVO> roles = new ArrayList<>();

    @Schema(description = "权限")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PermissionSimpleVO> permissions = new ArrayList<>();

    public UserInfoVO(User user, List<Role> roles, List<Permission> permissions) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.userStatus = user.getUserStatus();
        this.phone = user.getPhone();
        this.avatar = user.getAvatar();
        this.email = user.getEmail();

        if (roles != null) {
            roles.forEach(role -> this.roles.add(new RoleSimpleVO(role.getId(), role.getName(), role.getInfo())));
        }
        if (permissions != null) {
            permissions.forEach(permission -> this.permissions.add(new PermissionSimpleVO(permission.getId(), permission.getName(), permission.getInfo())));
        }
    }

}
