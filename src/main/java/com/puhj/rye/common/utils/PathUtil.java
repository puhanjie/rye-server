package com.puhj.rye.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author puhanjie
 * @description 访问基础路径工具类
 * @create 2022-12-14
 */
public class PathUtil {

    public static String getBasePath(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        String port = String.valueOf(request.getServerPort());

        return scheme + "://" + serverName + ":" + port;
    }

}
