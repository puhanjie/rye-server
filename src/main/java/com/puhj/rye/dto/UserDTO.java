package com.puhj.rye.dto;

import com.puhj.rye.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author puhanjie
 * @description 用户参数对象
 * @create 2023-07-22
 */
@Schema(name = "UserDTO", description = "用户参数对象")
@Data
public class UserDTO {

    @Schema(description = "用户id")
    private Integer id;

    @Schema(description = "部门")
    private Integer department;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "性别")
    private String sex;

    @Schema(description = "用户状态")
    private String userStatus;

    @Schema(description = "登陆密码")
    private String password;

    @Schema(description = "手机")
    private String phone;

    @Schema(description = "头像url")
    private String avatar;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "角色,id数组")
    private List<Integer> roles;

    @Schema(description = "岗位,id数组")
    private List<Integer> posts;

    public User entity() {
        User user = new User();
        user.setId(this.id);
        user.setDepartment(this.department);
        user.setUsername(this.username);
        user.setName(this.name);
        user.setSex(this.sex);
        user.setUserStatus(this.userStatus);
        user.setPassword(this.password);
        user.setPhone(this.phone);
        user.setAvatar(this.avatar);
        user.setEmail(this.email);
        return user;
    }

}
