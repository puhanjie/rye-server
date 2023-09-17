package com.puhj.rye.vo;

import com.puhj.rye.bo.DepartmentTreeBO;
import com.puhj.rye.bo.DictionaryBO;
import com.puhj.rye.bo.RoleBO;
import com.puhj.rye.bo.UserBO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author puhanjie
 * @description 部门表单选项数据
 * @create 2023-09-05
 */
@Schema(name = "DepartmentOptionsVO", description = "部门表单选项数据")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentOptionsVO {

    @Schema(description = "部门状态字典")
    List<DictionaryBO> deptStatus;

    @Schema(description = "用户数据")
    List<UserBO> users;

    @Schema(description = "部门数据")
    List<DepartmentTreeBO> departments;

    @Schema(description = "角色数据")
    List<RoleBO> roles;

}
