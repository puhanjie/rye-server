package com.puhj.rye.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author puhanjie
 * @description 数据库数据操作时间记录类
 * @create 2023-09-25
 */
@Component
public class OperateTimeConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 设置数据库表数据插入时创建时间和更新时间的值
        Date date = new Date();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = date.toInstant().atZone(zoneId).toLocalDateTime();
        this.setFieldValByName("createTime", localDateTime, metaObject);
        this.setFieldValByName("updateTime", localDateTime, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 设置数据库表数据更新时更新时间的值
        Date date = new Date();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = date.toInstant().atZone(zoneId).toLocalDateTime();
        this.setFieldValByName("updateTime", localDateTime, metaObject);
    }

}
