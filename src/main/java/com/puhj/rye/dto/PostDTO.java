package com.puhj.rye.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author puhanjie
 * @description 岗位参数对象
 * @create 2023-08-29
 */
@Schema(name = "PostDTO", description = "岗位参数对象")
@Data
public class PostDTO {

    @Schema(description = "岗位id")
    private Integer id;

    @Schema(description = "岗位编码")
    private String code;

    @Schema(description = "岗位名称")
    private String name;

    @Schema(description = "岗位状态")
    private String postStatus;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "角色,id数组")
    private List<Integer> roles;

}
