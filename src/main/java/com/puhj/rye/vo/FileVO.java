package com.puhj.rye.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 文件返回数据对象
 * @create 2022-3-29
 */
@Schema(name = "FileVO", description = "文件返回数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVO {

    @Schema(description = "文件路径")
    private String path;

    @Schema(description = "文件名")
    private String name;

}
