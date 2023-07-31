package com.puhj.rye.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 用户状态数据字典对象
 * @create 2023-07-31
 */
@Schema(name = "UserStatusVO", description = "用户状态数据字典对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusVO {

    @Schema(description = "字典值")
    private String itemValue;

    @Schema(description = "字典文本")
    private String itemText;

}
