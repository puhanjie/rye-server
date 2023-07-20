package com.puhj.rye.controller.api.v1;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作日志表 前端控制器
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Tag(name = "日志接口", description = "日志操作相关接口")
@RestController
@RequestMapping("/api/v1/log")
public class LogController {

}
