package com.puhj.rye.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.puhj.rye.common.utils.DateUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

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
        this.setFieldValByName("createTime", DateUtil.getLocalDateTime(new Date()), metaObject);
        this.setFieldValByName("updateTime", DateUtil.getLocalDateTime(new Date()), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 设置数据库表数据更新时更新时间的值
        this.setFieldValByName("updateTime", DateUtil.getLocalDateTime(new Date()), metaObject);
    }

}
