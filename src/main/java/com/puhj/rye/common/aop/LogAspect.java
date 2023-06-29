package com.puhj.rye.common.aop;

import com.puhj.rye.common.utils.JwtUtil;
import com.puhj.rye.entity.Log;
import com.puhj.rye.entity.User;
import com.puhj.rye.service.LogService;
import com.puhj.rye.service.UserService;
import com.puhj.rye.vo.ResultVO;
import com.puhj.rye.vo.TokenVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author puhanjie
 * @description 操作日志记录类
 * @create 2022/12/1 19:50
 * @modify 2022/12/1 19:50
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

    // 定义切点
    @Pointcut("execution(public * com.puhj.rye.common.advices.ResponseAdvice.beforeBodyWrite(..))")
    public void resultPointcut() {
    }

    @AfterReturning(value = "resultPointcut()", returning = "result")
    public void getResult(Object result) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 处理token获取为null异常，避免登陆接口调用时用户名或密码错误异常无法被捕获问题
        String token;
        try {
            if ("/api/v1/user/login".equals(request.getRequestURI())) {
                token = ((TokenVO) ((ResultVO<?>) result).getData()).getToken();
            } else {
                token = request.getHeader("Authorization").split(" ")[1];
            }
        } catch (NullPointerException e) {
            ResultVO<?> failRes = (ResultVO<?>) result;
            log.error("==> 接口：" + failRes.getRequest() + " 被调用 - 【状态：" + failRes.getCode() + "|" + failRes.getMessage() + "】");
            log.error("token is null", e);
            return;
        }

        User user = this.userService.getByUsername(JwtUtil.getTokenInfo(token));
        ResultVO<?> res = (ResultVO<?>) result;
        Log operateLog = new Log(res.getCode(), res.getMessage(), user.getId(), user.getUsername(), res.getRequest());
        // 记录操作日志和系统运行日志
        this.logService.add(operateLog);
        log.info("==> 接口：" + res.getRequest() + " 被调用 - 【操作人：" + user.getUsername() + "】 - 【状态：" + res.getCode() + "|" + res.getMessage() + "】");
    }

}
