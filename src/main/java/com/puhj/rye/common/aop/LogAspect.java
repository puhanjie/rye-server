package com.puhj.rye.common.aop;

import com.puhj.rye.common.utils.JwtUtil;
import com.puhj.rye.entity.Log;
import com.puhj.rye.entity.User;
import com.puhj.rye.service.LogService;
import com.puhj.rye.service.UserService;
import com.puhj.rye.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author puhanjie
 * @description 操作日志记录类
 * @create 2022-12-1
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    private final UserService userService;

    private final LogService logService;

    public LogAspect(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    @AfterReturning(value = "execution(public * com.puhj.rye.common.advices.ResponseAdvice.beforeBodyWrite(..))", returning = "result")
    public void getResult(Object result) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 登陆接口暂不记录操作日志,因为无法从request中获取到token记录操作人
        if ("/api/v1/user/login".equals(request.getRequestURI())) {
            return;
        }

        String token = request.getHeader("Authorization").split(" ")[1];
        User user = this.userService.getByUsername(JwtUtil.getTokenInfo(token));
        ResponseVO<?> res = (ResponseVO<?>) result;
        Log operateLog = new Log(res.getCode(), res.getMessage(), user.getId(), user.getUsername(), res.getRequest());
        // 记录操作日志和系统运行日志
        this.logService.add(operateLog);
        log.info("==> 接口：" + res.getRequest() + " 被调用 - [操作人：" + user.getUsername() + "] - [状态：" + res.getCode() + "|" + res.getMessage() + "]");
    }

}
