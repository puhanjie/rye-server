package com.puhj.rye.common.constant;

import lombok.Getter;

/**
 * @author puhanjie
 * @description 返回结果状态码配置
 * @create 2022-3-20
 */
@Getter
public enum ResultCode {

    SUCCESS(0, "成功", 200),
    FAIL(-1, "失败", 500),
    // 用户类异常码
    AUTHENTICATION_FAIL(10000, "非法身份", 403),
    PASSWORD_ERROR(10001, "密码错误", 403),
    NOT_FOUND_USER(10002, "用户不存在", 404),
    CURRENT_PASSWORD_ERROR(10003, "当前密码错误", 403),
    NOT_ADMIN(10004, "无管理员权限", 403),
    USER_STATUS_ERROR(10004, "用户状态异常", 403),
    // 资源类异常码
    NO_HANDLE_FOUND(20000, "资源不存在", 404),
    ACCESS_DENIED(20001, "无资源访问权限", 403);

    private final Integer code;

    private final String message;

    private final Integer httpCode;

    ResultCode(Integer code, String message, Integer httpCode) {
        this.code = code;
        this.message = message;
        this.httpCode = httpCode;
    }

    @Override
    public String toString() {
        return "ResultCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", httpCode=" + httpCode +
                '}';
    }

}
