package com.puhj.rye.common.auth;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author puhanjie
 * @description 认证Token类
 * @create 2022/3/19 00:40
 * @modify 2022/3/19 00:40
 */
public class AuthToken implements AuthenticationToken {

    private final String token;

    public AuthToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
