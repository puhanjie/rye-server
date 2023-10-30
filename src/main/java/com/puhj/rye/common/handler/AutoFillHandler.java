package com.puhj.rye.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.puhj.rye.common.utils.DateUtil;
import com.puhj.rye.common.utils.SubjectUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author puhanjie
 * @description 数据库数据操作时间记录类,需要在实体类公共填充字段注解@TableField中加上参数fill的值
 * @create 2023-09-25
 */
@Component
public class AutoFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 设置数据库表数据插入时创建时间和更新时间的值
        this.setFieldValByName("createTime", DateUtil.getLocalDateTime(new Date()), metaObject);
        this.setFieldValByName("updateTime", DateUtil.getLocalDateTime(new Date()), metaObject);

        // 设置数据库表数据插入时创建者和更新者的值
        Integer currentUserId = SubjectUtil.getSubjectId();
        this.setFieldValByName("createUser", currentUserId, metaObject);
        this.setFieldValByName("updateUser", currentUserId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 设置数据库表数据更新时更新时间的值
        this.setFieldValByName("updateTime", DateUtil.getLocalDateTime(new Date()), metaObject);

        // 设置数据库表数据更新时更新者的值
        Integer currentUserId = SubjectUtil.getSubjectId();
        this.setFieldValByName("updateUser", currentUserId, metaObject);
    }

}
