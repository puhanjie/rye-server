package com.puhj.rye.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 用户简要信息数据对象
 * @create 2023-09-10
 */
@Schema(name = "UserBO", description = "用户简要信息数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBO {

    @Schema(description = "用户id")
    private Integer id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "姓名")
    private String name;

}
