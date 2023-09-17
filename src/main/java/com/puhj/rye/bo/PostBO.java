package com.puhj.rye.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 岗位简要信息数据对象
 * @create 2023-08-29
 */
@Schema(name = "PostBO", description = "岗位简要信息数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostBO {

    @Schema(description = "岗位id")
    private Integer id;

    @Schema(description = "岗位编码")
    private String code;

    @Schema(description = "岗位名称")
    private String name;

}
