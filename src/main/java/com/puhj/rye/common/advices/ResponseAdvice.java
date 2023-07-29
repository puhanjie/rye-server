package com.puhj.rye.common.advices;

import com.puhj.rye.common.constant.ResultCode;
import com.puhj.rye.common.exception.HttpException;
import com.puhj.rye.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author puhanjie
 * @description 请求成功后Controller返回数据统一拦截处理，包括全局异常捕获
 * @create 2022-3-21
 */
@Slf4j
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    /*
     * 判断是否要执行 beforeBodyWrite 方法（true:执行;false:不执行）
     * 因整合了springdoc,所以springdoc相关资源返回false,不进行拦截
     */
    @Override
    public boolean supports(@NotNull MethodParameter returnType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.getDeclaringClass().getName().contains("springdoc");
    }

    @Override
    public Object beforeBodyWrite(Object body, @NotNull MethodParameter returnType, @NotNull MediaType selectedContentType, @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType, @NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response) {
        // 异常捕获后返回的结果是ResponseVO类型,直接返回
        if (body instanceof ResponseVO) {
            return body;
        }

        // 请求正常时统一数据响应格式处理
        String requestUrl = request.getURI().getPath();
        String method = String.valueOf(request.getMethod());
        return ResponseVO.success(body, method + " " + requestUrl);
    }

    /* 全局异常捕获 */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseVO<?> handleException(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        return ResponseVO.fail(ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage(), method + " " + requestUrl);
    }

    // 自定义业务异常捕获
    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<?> handleSystemException(HttpServletRequest request, HttpException e) {
        log.error(e.getCode().toString() + '|' + e.getMessage(), e);
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpCode());
        ResponseVO<Object> responseVO = ResponseVO.fail(e.getCode(), e.getMessage(), method + " " + requestUrl);
        return new ResponseEntity<>(responseVO, Objects.requireNonNullElse(httpStatus, HttpStatus.BAD_REQUEST));
    }

    // shiro认证异常捕获
    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ResponseVO<?> handleUserNotFoundErrorException(HttpServletRequest request, AuthenticationException e) {
        log.error(e.getMessage(), e);
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        return ResponseVO.fail(ResultCode.AUTHENTICATION_FAIL.getCode(), ResultCode.AUTHENTICATION_FAIL.getMessage(), method + " " + requestUrl);
    }

    // shiro授权异常捕获
    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ResponseVO<?> handleNoPermissionException(HttpServletRequest request, AuthorizationException e) {
        log.error(e.getMessage(), e);
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        return ResponseVO.fail(ResultCode.ACCESS_DENIED.getCode(), ResultCode.ACCESS_DENIED.getMessage(), method + " " + requestUrl);
    }

    // 请求资源不存在异常捕获
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseVO<?> handleNoHandlerFoundException(HttpServletRequest request, NoHandlerFoundException e) {
        log.error(e.getMessage(), e);
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        return ResponseVO.fail(ResultCode.NO_HANDLE_FOUND.getCode(), ResultCode.NO_HANDLE_FOUND.getMessage(), method + " " + requestUrl);
    }

}
