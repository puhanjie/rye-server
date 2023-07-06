package com.puhj.rye.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puhj.rye.common.constant.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puhanjie
 * @description 统一响应数据VO
 * @create 2022-3-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {

    @JsonProperty(index = 0)
    private Integer code;

    @JsonProperty(index = 1)
    private String message;

    @JsonProperty(index = 2)
    private String request;

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
