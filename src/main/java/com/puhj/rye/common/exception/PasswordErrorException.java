package com.puhj.rye.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author puhanjie
 * @description 密码错误异常类
 * @create 2022-11-13
 */
@Setter
@Getter
public class PasswordErrorException extends RuntimeException {

    protected Integer code;

    protected String msg;

    public PasswordErrorException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
