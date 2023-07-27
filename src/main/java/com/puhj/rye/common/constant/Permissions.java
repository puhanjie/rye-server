package com.puhj.rye.common.constant;

/**
 * @author puhanjie
 * @description 系统权限配置类
 * @create 2022-3-19
 */
public final class Permissions {

    public static final String ADMIN = "app:admin";

    /* 用户管理 */
    public static final class User {
        public static final String VIEW = "user:view";
        public static final String ADD = "user:add";
        public static final String UPDATE = "user:update";
        public static final String DELETE = "user:delete";
        public static final String BATCHDELETE = "user:batchDelete";
    }

    /* 角色管理 */
    public static final class Role {
        public static final String VIEW = "role:view";
        public static final String ADD = "role:add";
        public static final String UPDATE = "role:update";
        public static final String DELETE = "role:delete";
        public static final String BATCHDELETE = "role:batchDelete";
    }

    /* 权限管理 */
    public static final class Permission {
        public static final String VIEW = "permission:view";
        public static final String ADD = "permission:add";
        public static final String UPDATE = "permission:update";
        public static final String DELETE = "permission:delete";
        public static final String BATCHDELETE = "permission:batchDelete";
    }

    /* 字典管理 */
    public static final class Dictionary {
        public static final String VIEW = "dictionary:view";
        public static final String ADD = "dictionary:add";
        public static final String UPDATE = "dictionary:update";
        public static final String DELETE = "dictionary:delete";
        public static final String BATCHDELETE = "dictionary:batchDelete";
    }

}
