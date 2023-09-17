package com.puhj.rye.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.puhj.rye.bo.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author puhanjie
 * @description 用户列表数据对象
 * @create 2023-6-15
 */
@Schema(name = "UserInfoVO", description = "用户列表数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO {

    @Schema(description = "用户id")
    private Integer id;

    @Schema(description = "部门")
    private DepartmentBO department;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "性别")
    private DictionaryBO sex;

    @Schema(description = "用户状态")
    private DictionaryBO userStatus;

    @Schema(description = "手机")
    private String phone;

    @Schema(description = "头像url")
    private String avatar;

    @Schema(description = "邮箱")
    private String email;

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

    @Schema(description = "角色")
    private List<RoleBO> roles;

    @Schema(description = "岗位")
    private List<PostBO> posts;

}
