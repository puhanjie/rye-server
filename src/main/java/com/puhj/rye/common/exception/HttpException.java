package com.puhj.rye.common.exception;

import com.puhj.rye.common.constant.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author puhanjie
 * @description 自定义系统异常类
 * @create 2023-07-29
 */
@Setter
@Getter
@AllArgsConstructor
public class HttpException extends RuntimeException {

    protected Integer code;

    protected String message;

    protected Integer httpCode;

    public HttpException(Result result) {
        this.code = result.getCode();
        this.message = result.getMessage();
        this.httpCode = result.getHttpCode();
    }

}
