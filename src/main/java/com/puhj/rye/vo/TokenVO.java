package com.puhj.rye.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description Token数据对象
 * @create 2023-6-14
 */
@Schema(name = "TokenVO", description = "Token数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenVO {

    @Schema(description = "token")
    private String token;

}
