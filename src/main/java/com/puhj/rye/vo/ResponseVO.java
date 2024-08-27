package com.puhj.rye.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puhj.rye.common.constant.Result;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 统一数据响应对象
 * @create 2022-3-19
 */
@Schema(name = "ResponseVO", description = "统一数据响应对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO<T> {

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

    public static <T> ResponseVO<T> success(T data, String request) {
        ResponseVO<T> responseVO = new ResponseVO<>();
        responseVO.setCode(Result.SUCCESS.getCode());
        responseVO.setMessage(Result.SUCCESS.getMessage());
        responseVO.setRequest(request);
        responseVO.setData(data);

        return responseVO;
    }

    public static <T> ResponseVO<T> fail(Integer code, String message, String request) {
        ResponseVO<T> responseVO = new ResponseVO<>();
        responseVO.setCode(code);
        responseVO.setMessage(message);
        responseVO.setRequest(request);

        return responseVO;
    }

}
