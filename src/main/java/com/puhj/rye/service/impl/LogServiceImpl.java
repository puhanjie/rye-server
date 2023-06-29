package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.entity.Log;
import com.puhj.rye.mapper.LogMapper;
import com.puhj.rye.service.LogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    private final LogMapper logMapper;

    public LogServiceImpl(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Override
    public void add(Log log) {
        this.logMapper.insert(log);
    }

}
