package com.puhj.rye.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author puhanjie
 * @description 用户状态异常类
 * @create 2023-07-27
 */
@Setter
@Getter
public class UserStatusException extends RuntimeException {

    protected Integer code;

    protected String msg;

    public UserStatusException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
