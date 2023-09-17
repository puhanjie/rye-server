package com.puhj.rye.vo;

import com.puhj.rye.bo.DictionaryBO;
import com.puhj.rye.bo.PermissionBO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author puhanjie
 * @description 角色表单选项数据
 * @create 2023-09-05
 */
@Schema(name = "RoleOptionsVO", description = "角色表单选项数据")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleOptionsVO {

    @Schema(description = "角色状态字典")
    List<DictionaryBO> roleStatus;

    @Schema(description = "权限数据")
    List<PermissionBO> permissions;

}
