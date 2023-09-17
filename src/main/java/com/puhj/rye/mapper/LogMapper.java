package com.puhj.rye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.vo.LogInfoVO;
import com.puhj.rye.entity.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 操作日志表 Mapper 接口
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Mapper
public interface LogMapper extends BaseMapper<Log> {

    Page<LogInfoVO> list(Page<LogInfoVO> page,
                         @Param("message") String message,
                         @Param("operateUser") String operateUser);

}
