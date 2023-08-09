package com.puhj.rye.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author puhanjie
 * @description 字典列表数据对象
 * @create 2023-08-09
 */
@Schema(name = "DictionaryListVO", description = "字典列表数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryListVO {

    @Schema(description = "字典id")
    private Integer id;

    @Schema(description = "字典名")
    private String dictName;

    @Schema(description = "字典文本")
    private String dictText;

    @Schema(description = "字典值")
    private String itemValue;

    @Schema(description = "字典值文本")
    private String itemText;

    @Schema(description = "描述")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    private Date updateTime;

}
