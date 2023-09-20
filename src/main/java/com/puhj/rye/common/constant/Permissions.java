package com.puhj.rye.common.constant;

/**
 * @author puhanjie
 * @description 系统权限配置类
 * @create 2022-3-19
 */
public final class Permissions {

    public static final String ADMIN = "admin";

    /* 用户管理 */
    public static final class User {
        public static final String VIEW = "user:view";
        public static final String ADD = "user:add";
        public static final String EDIT = "user:edit";
        public static final String DELETE = "user:delete";
    }

    /* 岗位管理 */
    public static final class Post {
        public static final String VIEW = "post:view";
        public static final String ADD = "post:add";
        public static final String EDIT = "post:edit";
        public static final String DELETE = "post:delete";
    }

    /* 部门管理 */
    public static final class Department {
        public static final String VIEW = "department:view";
        public static final String ADD = "department:add";
        public static final String EDIT = "department:edit";
        public static final String DELETE = "department:delete";
    }

    /* 角色管理 */
    public static final class Role {
        public static final String VIEW = "role:view";
        public static final String ADD = "role:add";
        public static final String EDIT = "role:edit";
        public static final String DELETE = "role:delete";
    }

    /* 权限管理 */
    public static final class Permission {
        public static final String VIEW = "permission:view";
        public static final String ADD = "permission:add";
        public static final String EDIT = "permission:edit";
        public static final String DELETE = "permission:delete";
    }

    /* 字典管理 */
    public static final class Dictionary {
        public static final String VIEW = "dictionary:view";
        public static final String ADD = "dictionary:add";
        public static final String EDIT = "dictionary:edit";
        public static final String DELETE = "dictionary:delete";
    }

    /* 文件管理 */
    public static final class File {
        public static final String VIEW = "file:view";
        public static final String UPLOAD = "file:upload";
        public static final String DOWNLOAD = "file:download";
        public static final String DELETE = "file:delete";
    }

    /* 日志管理 */
    public static final class Log {
        public static final String VIEW = "log:view";
        public static final String DELETE = "log:delete";
        public static final String EMPTY = "log:empty";
    }

}
