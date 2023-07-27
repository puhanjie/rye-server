package com.puhj.rye.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author puhanjie
 * @since 2023-07-27
 */
@Schema(name = "Dictionary", description = "字典表")
@Data
@TableName("dictionary")
public class Dictionary implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "字典名")
    @TableField("dict_name")
    private String dictName;

    @Schema(description = "字典文本")
    @TableField("dict_text")
    private String dictText;

    @Schema(description = "字典值")
    @TableField("item_value")
    private String itemValue;

    @Schema(description = "字典值文本")
    @TableField("item_text")
    private String itemText;

    @Schema(description = "描述")
    @TableField("description")
    private String description;

}
