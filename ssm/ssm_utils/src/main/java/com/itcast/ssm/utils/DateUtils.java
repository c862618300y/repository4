package com.itcast.ssm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换工具类
 */
public class DateUtils {

    /**
     * 日期转换成字符串
     * @param date 日期
     * @param pattern 转换成的格式
     * @return
     */
    public static String dateConverString(Date date,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return format;
    }

    /**
     *
     * @param str
     * @param pattern 转换成的格式
     * @return
     * @throws Exception
     */
    public static Date StringConverDate(String str,String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(str);
        return date;
    }
}
