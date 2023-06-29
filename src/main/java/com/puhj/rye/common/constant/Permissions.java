package com.puhj.rye.common.constant;

/**
 * @author puhanjie
 * @description 系统权限配置类
 * @create 2022/3/19 00:40
 * @modify 2022/3/19 00:40
 */
public final class Permissions {

    public static final String ADMIN = "app:admin";

    /* 系统管理模块 */
    public static final class System {

        /* 用户管理 */
        public static final class User {
            public static final String VIEW = "system:user:view";
            public static final String ADD = "system:user:add";
            public static final String UPDATE = "system:user:update";
            public static final String DELETE = "system:user:delete";
        }

        /* 角色管理 */
        public static final class Role {
            public static final String VIEW = "system:role:view";
            public static final String ADD = "system:role:add";
            public static final String UPDATE = "system:role:update";
            public static final String DELETE = "system:role:delete";
        }

        /* 权限管理 */
        public static final class Permission {
            public static final String VIEW = "system:permission:view";
            public static final String ADD = "system:permission:add";
            public static final String UPDATE = "system:permission:update";
            public static final String DELETE = "system:permission:delete";
        }
    }

}
