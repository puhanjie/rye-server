package com.puhj.rye.common.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puhj.rye.common.utils.SubjectUtil;
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
import java.io.IOException;

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

        User user = this.userService.getByUsername(SubjectUtil.getSubjectName());
        ResponseVO<?> res;
        // 若result为String类型,说明在ResponseAdvice.beforeBodyWrite中已经返回json字符串,则需要反序列化为ResponseVO对象
        if (result instanceof String) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                res = mapper.readValue((String) result, ResponseVO.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            res = (ResponseVO<?>) result;
        }
        Log operateLog = new Log(res.getRequest(), res.getCode(), res.getMessage(), user.getId());
        // 记录操作日志和系统运行日志
        this.logService.add(operateLog);
        log.info("==> 接口：" + res.getRequest() + " 被调用 - [操作人：" + user.getUsername() + "] - [状态：" + res.getCode() + " | " + res.getMessage() + "]");
    }

}
