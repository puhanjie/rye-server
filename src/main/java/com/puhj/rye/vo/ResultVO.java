package com.puhj.rye.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puhj.rye.common.constant.ResultCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 统一数据响应对象
 * @create 2022-3-19
 */
@Schema(name = "ResultVO", description = "统一数据响应对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {

    @Schema(description = "响应状态码")
    @JsonProperty(index = 0)
    private Integer code;

    @Schema(description = "响应消息")
    @JsonProperty(index = 1)
    private String message;

    @Schema(description = "请求url")
    @JsonProperty(index = 2)
    private String request;

    @Schema(description = "响应数据")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(index = 3)
    private T data;

    public static <T> ResultVO<T> success(T data, String request) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(ResultCode.SUCCESS.getCode());
        resultVO.setMessage(ResultCode.SUCCESS.getMessage());
        resultVO.setRequest(request);
        resultVO.setData(data);

        return resultVO;
    }

    public static <T> ResultVO<T> fail(Integer code, String message, String request) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        resultVO.setRequest(request);

        return resultVO;
    }

}
