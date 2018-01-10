package com.sunxiaoyu.utils.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 时间转换工具类
 *
 * Created by sunxiaoyu
 *
 */
public class TimeUtils {


    public static String timePattern1 = "yyyy/MM/dd HH:mm:ss";
    public static String timePattern2 = "yyyy-MM-dd HH:mm";
    public static String timePattern3 = "yyyy年MM月dd日 HH时mm分ss秒";
    public static String timePattern4 = "yyyy年MM月dd日 HH:mm";

    /**
     *   long -- String
     */
    public static String longTime2String(long temp, String pattern){
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(new Date(temp));
    }

    /**
     *   date -- String
     */
    public static String dateTime2String(Date date, String pattern){
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     *  String -- long
     * @param timeStr  例如：08/31/2006 21:08:00
     * @param pattern  例如：MM/dd/yyyy HH:mm:ss  和timeStr时间格式一致
     * @return  时间戳
     */
    public static long StringTime2Long(String timeStr, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            Date curDate = formatter.parse(timeStr);
            return curDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *  String -- Date
     * @param timeStr  例如：08/31/2006 21:08:00
     * @param pattern  例如：MM/dd/yyyy HH:mm:ss  和timeStr时间格式一致
     * @return  时间戳
     */
    public static Date StringTime2Date(String timeStr, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
