package com.puhj.rye.common.utils;

import org.apache.shiro.SecurityUtils;

import java.util.Objects;

/**
 * @author puhanjie
 * @description 获取当前登陆主体用户信息工具类
 * @create 2023-08-26
 */
public class SubjectUtil {

    public static Integer getSubjectId() {
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        return Objects.requireNonNull(JwtUtil.getTokenInfo(token, "id")).asInt();
    }

    public static String getSubjectName() {
        String token = SecurityUtils.getSubject().getPrincipal().toString();
        return Objects.requireNonNull(JwtUtil.getTokenInfo(token, "username")).asString();
    }

}
