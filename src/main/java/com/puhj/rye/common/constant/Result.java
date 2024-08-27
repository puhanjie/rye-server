package com.puhj.rye.common.constant;

import lombok.Getter;

/**
 * @author puhanjie
 * @description 返回结果状态码配置
 * @create 2022-3-20
 */
@Getter
public enum Result {

    SUCCESS(0, "成功", 200),
    FAIL(-1, "失败", 500),
    // 用户模块异常码
    AUTHENTICATION_FAIL(10000, "非法身份", 403),
    PASSWORD_ERROR(10001, "密码错误", 403),
    NOT_FOUND_USER(10002, "用户不存在", 404),
    CURRENT_PASSWORD_ERROR(10003, "当前密码错误", 403),
    NOT_ADMIN(10004, "无管理员权限", 403),
    USER_STATUS_ERROR(10004, "用户状态异常", 403),
    USER_ADD_ERROR(10005, "新增用户失败", 500),
    USER_EDIT_ERROR(10006, "编辑用户失败", 500),
    USER_SET_ROLES_ERROR(10007, "用户分配角色失败", 500),
    USER_SET_POSTS_ERROR(10008, "用户分配岗位失败", 500),
    //角色模块异常码
    ROLE_ADD_ERROR(11000, "新增角色失败", 500),
    ROLE_EDIT_ERROR(11001, "编辑角色失败", 500),
    ROLE_SET_PERMISSIONS_ERROR(11002, "角色分配权限失败", 500),
    // 权限模块异常码
    PERMISSION_ADD_ERROR(12000, "新增权限失败", 500),
    PERMISSION_EDIT_ERROR(12001, "编辑权限失败", 500),
    //岗位模块异常码
    POST_ADD_ERROR(13000, "新增岗位失败", 500),
    POST_EDIT_ERROR(13001, "编辑岗位失败", 500),
    POST_SET_ROLES_ERROR(13002, "岗位分配角色失败", 500),
    // 部门模块异常码
    DEPARTMENT_ADD_ERROR(14000, "新增部门失败", 500),
    DEPARTMENT_EDIT_ERROR(14001, "编辑部门失败", 500),
    DEPARTMENT_SET_ROLES_ERROR(14002, "部门分配角色失败", 500),
    // 字典模块异常码
    DICTIONARY_ADD_ERROR(15000, "新增权限失败", 500),
    DICTIONARY_EDIT_ERROR(15001, "编辑权限失败", 500),
    // 资源访问异常码
    NO_HANDLE_FOUND(20000, "资源不存在", 404),
    ACCESS_DENIED(20001, "无资源访问权限", 403);

    private final Integer code;

    private final String message;

    private final Integer httpCode;

    Result(Integer code, String message, Integer httpCode) {
        this.code = code;
        this.message = message;
        this.httpCode = httpCode;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", httpCode=" + httpCode +
                '}';
    }

}
