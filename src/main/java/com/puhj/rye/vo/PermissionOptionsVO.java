package com.puhj.rye.vo;

import com.puhj.rye.bo.DictionaryBO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author puhanjie
 * @description 权限表单选项数据
 * @create 2023-09-05
 */
@Schema(name = "PermissionOptionsVO", description = "权限表单选项数据")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionOptionsVO {

    @Schema(description = "权限状态字典")
    List<DictionaryBO> permissionStatus;

}
