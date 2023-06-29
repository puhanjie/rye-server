package com.puhj.rye.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author puhanjie
 * @description 日期格式化工具类
 * @create 2022/12/14 12:08
 * @modify 2022/12/14 12:08
 */
public class DateUtil {

    public static String getDateFormat(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

}
