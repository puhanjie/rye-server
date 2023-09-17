package com.puhj.rye.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author puhanjie
 * @description 字典参数对象
 * @create 2023-08-26
 */
@Schema(name = "DictionaryDTO", description = "字典参数对象")
@Data
public class DictionaryDTO {

    @Schema(description = "字典id")
    private Integer id;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "字典名称")
    private String dictName;

    @Schema(description = "字典键值")
    private String dictValue;

    @Schema(description = "字典标签")
    private String dictLabel;

    @Schema(description = "描述")
    private String description;

}
