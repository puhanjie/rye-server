package com.puhj.rye.vo;

import com.puhj.rye.bo.DepartmentTreeBO;
import com.puhj.rye.bo.DictionaryBO;
import com.puhj.rye.bo.PostBO;
import com.puhj.rye.bo.RoleBO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author puhanjie
 * @description 用户表单选项数据
 * @create 2023-09-05
 */
@Schema(name = "UserOptionsVO", description = "用户表单选项数据")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOptionsVO {

    @Schema(description = "部门数据")
    List<DepartmentTreeBO> departments;

    @Schema(description = "岗位数据")
    List<PostBO> posts;

    @Schema(description = "角色数据")
    List<RoleBO> roles;

    @Schema(description = "性别字典")
    List<DictionaryBO> sex;

    @Schema(description = "用户状态字典")
    List<DictionaryBO> userStatus;

}
