package com.puhj.rye.common.constant;

import lombok.Getter;

/**
 * @author puhanjie
 * @description 返回结果状态码配置
 * @create 2022-3-20
 */
@Getter
public enum ResultCode {

    SUCCESS(0, "成功"),
    FAIL(-1, "失败"),
    AUTHENTICATION_FAIL(10001, "非法身份"),
    PASSWORD_ERROR(10002, "密码错误"),
    ACCESS_DENIED(10003, "无资源访问权限"),
    NOT_FOUND_USER(10004, "用户不存在"),
    NO_HANDLE_FOUND(10005, "资源不存在"),
    CURRENT_PASSWORD_ERROR(10006, "当前密码错误"),
    USER_STATUS_ERROR(10007, "用户状态异常");

    private final Integer code;

    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

}
