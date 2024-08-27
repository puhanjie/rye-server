package com.puhj.rye.common.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puhj.rye.common.utils.JwtUtil;
import com.puhj.rye.common.utils.SubjectUtil;
import com.puhj.rye.entity.Log;
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

    private final LogService logService;

    public LogAspect(UserService userService, LogService logService) {
        this.logService = logService;
    }

    @AfterReturning(value = "execution(public * com.puhj.rye.common.advices.ResponseAdvice.beforeBodyWrite(..))", returning = "result")
    public void getResult(Object result) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        ResponseVO<?> responseVO;
        if (result instanceof String) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                responseVO = mapper.readValue((String) result, ResponseVO.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            responseVO = (ResponseVO<?>) result;
        }

        Integer userId;
        String username;
        if ("/api/v1/user/login".equals(request.getRequestURI())) {
            userId = JwtUtil.getTokenInfo(responseVO.getData().toString(), "id").asInt();
            username = JwtUtil.getTokenInfo(responseVO.getData().toString(), "username").asString();
        } else {
            userId = SubjectUtil.getSubjectId();
            username = SubjectUtil.getSubjectName();
        }

        Log operateLog = new Log(responseVO.getRequest(), responseVO.getCode(), responseVO.getMessage(), userId);
        // 记录操作日志和系统运行日志
        this.logService.add(operateLog);
        log.info("==> 接口：" + responseVO.getRequest() + " 被调用 - [操作人：" + username + "] - [状态：" + responseVO.getCode() + " | " + responseVO.getMessage() + "]");
    }

}
