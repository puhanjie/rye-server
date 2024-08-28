package com.puhj.rye.controller.api.v1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.vo.LogInfoVO;
import com.puhj.rye.common.constant.Permissions;
import com.puhj.rye.service.LogService;
import com.puhj.rye.vo.PageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @Operation(summary = "删除日志", description = "根据日志id数组删除日志")
    @DeleteMapping
    @RequiresPermissions(Permissions.Log.DELETE)
    public boolean remove(@RequestBody List<Integer> ids) {
        return this.logService.removeByIds(ids);
    }

    @Operation(summary = "清空日志", description = "清空所有日志")
    @DeleteMapping("/empty")
    @RequiresPermissions(Permissions.Log.EMPTY)
    public boolean empty() {
        return this.logService.remove(new QueryWrapper<>());
    }

    @Operation(summary = "查询日志列表", description = "分页查询日志列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "分页查询页码"),
            @Parameter(name = "pageSize", description = "每页数据大小"),
            @Parameter(name = "message", description = "响应消息"),
            @Parameter(name = "operateUser", description = "操作人")
    })
    @GetMapping("/list")
    @RequiresPermissions(Permissions.Log.VIEW)
    public PageVO<LogInfoVO> list(
            @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "message", required = false) String message,
            @RequestParam(value = "operateUser", required = false) String operateUser) {
        Page<LogInfoVO> page = new Page<>(pageNum, pageSize);
        return this.logService.list(page, message, operateUser);
    }

}
