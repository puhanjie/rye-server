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
 * @description 文件列表数据对象Ï
 * @create 2023-09-18
 */
@Schema(name = "FileInfoVO", description = "文件列表数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoVO {

    @Schema(description = "文件id")
    private Integer id;

    @Schema(description = "文件地址")
    private String path;

    @Schema(description = "文件名")
    private String name;

    @Schema(description = "文件大小(Byte)")
    private Long fileSize;

    @Schema(description = "uuid")
    private String uuid;

    @Schema(description = "上传者(用户id)")
    private UserBO uploadUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "上传时间")
    private LocalDateTime uploadTime;

}
