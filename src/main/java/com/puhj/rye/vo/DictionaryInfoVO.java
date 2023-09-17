package com.puhj.rye.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.puhj.rye.bo.UserBO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author puhanjie
 * @description 字典列表数据对象
 * @create 2023-08-09
 */
@Schema(name = "DictionaryInfoVO", description = "字典列表数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryInfoVO {

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

    @Schema(description = "创建者")
    private UserBO createUser;

    @Schema(description = "更新者")
    private UserBO updateUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
