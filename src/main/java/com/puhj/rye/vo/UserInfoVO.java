package com.puhj.rye.vo;

import com.puhj.rye.entity.Permission;
import com.puhj.rye.entity.Role;
import com.puhj.rye.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author puhanjie
 * @description 用户基本信息VO
 * @create 2022/3/21 19:35
 * @modify 2022/3/21 19:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO {

    private Integer id;

    private String username;

    private String phone;

    private String avatar;

    private String email;

    private List<RoleSimpleVO> roles = new ArrayList<>();

    private List<PermissionSimpleVO> permissions = new ArrayList<>();

    public UserInfoVO(User user, List<Role> roles, List<Permission> permissions) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.avatar = user.getAvatar();
        this.email = user.getEmail();

        roles.forEach(role -> this.roles.add(new RoleSimpleVO(role.getId(), role.getName(), role.getInfo())));
        permissions.forEach(permission -> this.permissions.add(new PermissionSimpleVO(permission.getId(), permission.getName(), permission.getInfo())));
    }

}
