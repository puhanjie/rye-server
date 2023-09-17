package com.puhj.rye.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puhj.rye.vo.LogInfoVO;
import com.puhj.rye.entity.Log;
import com.puhj.rye.vo.PageVO;

/**
 * <p>
 * 操作日志表 服务类
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
public interface LogService extends IService<Log> {

    void add(Log log);

    PageVO<LogInfoVO> list(Page<LogInfoVO> page, String message, String operateUser);

}
