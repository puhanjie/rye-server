package com.puhj.rye.common.advices;

import com.puhj.rye.common.constant.ResultCode;
import com.puhj.rye.common.exception.NotFoundUserException;
import com.puhj.rye.common.exception.PasswordErrorException;
import com.puhj.rye.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author puhanjie
 * @description 请求成功后Controller返回数据统一拦截处理，包括全局异常捕获
 * @create 2022/3/21 18:35
 * @modify 2022/3/21 18:35
 */
@Slf4j
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    /*
     * 判断是否要执行 beforeBodyWrite 方法（true:执行;false:不执行）
     * 因整合了swagger，所以swagger相关资源返回false，不进行拦截
     */
    @Override
    public boolean supports(@NotNull MethodParameter returnType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.getDeclaringClass().getName().contains("springfox");
    }

    @Override
    public Object beforeBodyWrite(Object body, @NotNull MethodParameter returnType, @NotNull MediaType selectedContentType, @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType, @NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response) {
        // 若已controller返回结果是ResultVO类型，则直接返回
        if (body instanceof ResultVO) {
            return body;
        }

        // 请求正常时统一数据响应格式处理
        String requestUrl = request.getURI().getPath();
        String method = String.valueOf(request.getMethod());
        return ResultVO.success(body, method + " " + requestUrl);
    }

    /* 全局异常捕获 */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<?> handleException(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        return ResultVO.fail(ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage(), method + " " + requestUrl);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ResultVO<?> handleUserNotFoundErrorException(HttpServletRequest request, AuthenticationException e) {
        log.error(e.getMessage(), e);
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        return ResultVO.fail(ResultCode.AUTHENTICATION_FAIL.getCode(), ResultCode.AUTHENTICATION_FAIL.getMessage(), method + " " + requestUrl);
    }

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ResultVO<?> handleNoPermissionException(HttpServletRequest request, AuthorizationException e) {
        log.error(e.getMessage(), e);
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        return ResultVO.fail(ResultCode.ACCESS_DENIED.getCode(), ResultCode.ACCESS_DENIED.getMessage(), method + " " + requestUrl);
    }

    @ExceptionHandler(value = NotFoundUserException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResultVO<?> handleNotFoundUserException(HttpServletRequest request, NotFoundUserException e) {
        log.error(e.getMessage(), e);
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        return ResultVO.fail(ResultCode.NOT_FOUND_USER.getCode(), ResultCode.NOT_FOUND_USER.getMessage(), method + " " + requestUrl);
    }

    @ExceptionHandler(value = PasswordErrorException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultVO<?> handlePasswordErrorException(HttpServletRequest request, PasswordErrorException e) {
        log.error(e.getMessage(), e);
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        return ResultVO.fail(e.getCode(), e.getMsg(), method + " " + requestUrl);
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResultVO<?> handleNoHandlerFoundException(HttpServletRequest request, NoHandlerFoundException e) {
        log.error(e.getMessage(), e);
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        return ResultVO.fail(ResultCode.NO_HANDLE_FOUND.getCode(), ResultCode.NO_HANDLE_FOUND.getMessage(), method + " " + requestUrl);
    }

}
