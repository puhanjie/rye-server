package com.puhj.rye.vo;

import com.puhj.rye.bo.*;
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
@Schema(name = "UserBasicInfoVO", description = "用户基本信息数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicInfoVO {

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

    @Schema(description = "角色")
    private List<RoleBO> roles = new ArrayList<>();

    @Schema(description = "岗位")
    private List<PostBO> posts = new ArrayList<>();

    @Schema(description = "权限")
    private List<PermissionBO> permissions = new ArrayList<>();

}
