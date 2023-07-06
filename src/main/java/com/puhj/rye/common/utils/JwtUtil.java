package com.puhj.rye.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * @author puhanjie
 * @description JWT工具类
 * @create 2022-3-19
 */
@Component
public class JwtUtil {

    private static String secret;

    private static Integer expiredTime;

    /**
     * 创建令牌
     */
    public static String createToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(JwtUtil.secret);
        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();
        calendar.add(Calendar.SECOND, JwtUtil.expiredTime);
        Date endTime = calendar.getTime();

        return JWT.create()
                .withClaim("username", username)
                .withIssuedAt(currentTime)
                .withExpiresAt(endTime)
                .sign(algorithm);
    }

    /**
     * 校验令牌
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtUtil.secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            // 验证token
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }

    /**
     * 解析令牌
     */
    public static String getTokenInfo(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 静态属性只能通过set函数注入
     */
    @Value("${jwt.secret}")
    public void setJwtKey(String secret) {
        JwtUtil.secret = secret;
    }

    @Value("${jwt.expired-time}")
    public void setExpiredTimeIn(Integer expiredTime) {
        JwtUtil.expiredTime = expiredTime;
    }

}
