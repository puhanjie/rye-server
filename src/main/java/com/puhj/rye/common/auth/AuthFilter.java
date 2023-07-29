package com.puhj.rye.common.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puhj.rye.common.constant.ResultCode;
import com.puhj.rye.common.utils.JwtUtil;
import com.puhj.rye.vo.ResultVO;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author puhanjie
 * @description Shiro认证过滤器
 * @create 2022-3-19
 */
public class AuthFilter extends FormAuthenticationFilter {

    /**
     * 返回true,直接允许访问url;返回false,进入onAccessDenied()根据返回值决定是否访问url
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token;

        try {
            token = req.getHeader("Authorization").split(" ")[1];
        } catch (Exception e) {
            // token获取失败,一般是token为空
            return false;
        }
        // token验证失败(仅验证合法性,不验证是否失效),返回false进入onAccessDenied方法执行
        if (!JwtUtil.verify(token)) {
            return false;
        }

        AuthToken authToken = new AuthToken(token);
        // 提交给realm进行登陆认证
        this.getSubject(request, response).login(authToken);
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // 因过滤器中的异常无法被springboot全局异常类捕获,因此发生异常需要给response对象写入信息返回给前端
        ResultVO<?> result = ResultVO.fail(ResultCode.AUTHENTICATION_FAIL.getCode(),
                ResultCode.AUTHENTICATION_FAIL.getMessage(),
                req.getMethod() + " " + req.getRequestURI());

        ObjectMapper objectMapper = new ObjectMapper();

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset = utf-8");
        resp.setStatus(ResultCode.AUTHENTICATION_FAIL.getHttpCode());
        resp.getWriter().write(objectMapper.writeValueAsString(result));
        return false;
    }

}
