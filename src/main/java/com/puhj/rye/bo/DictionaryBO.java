package com.puhj.rye.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 用户状态数据字典对象
 * @create 2023-07-31
 */
@Schema(name = "DictionaryBO", description = "字典项数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryBO {

    @Schema(description = "字典键值")
    private String dictValue;

    @Schema(description = "字典标签")
    private String dictLabel;

}
