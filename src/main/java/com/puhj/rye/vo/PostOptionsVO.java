package com.puhj.rye.vo;

import com.puhj.rye.bo.DictionaryBO;
import com.puhj.rye.bo.RoleBO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author puhanjie
 * @description 岗位表单选项数据
 * @create 2023-09-05
 */
@Schema(name = "PostOptionsVO", description = "岗位表单选项数据")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostOptionsVO {

    @Schema(description = "岗位状态字典")
    List<DictionaryBO> postStatus;

    @Schema(description = "角色数据")
    List<RoleBO> roles;

}
