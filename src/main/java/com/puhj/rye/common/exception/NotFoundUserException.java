package com.puhj.rye.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author puhanjie
 * @description 用户不存在异常类
 * @create 2022-3-19
 */
@Setter
@Getter
public class NotFoundUserException extends RuntimeException {

    protected Integer code;

    protected String msg;

    public NotFoundUserException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
