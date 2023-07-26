package com.puhj.rye.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 用户头像返回数据对象
 * @create 2023-07-26
 */
@Schema(name = "AvatarVO", description = "用户头像返回数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvatarVO {

    @Schema(description = "头像url")
    private String url;

}
