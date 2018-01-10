package com.sunxiaoyu.utils.core.utils;

/**
 * 处理字符串工具类
 * Created by Administrator on 2018/1/10 0010.
 */
public class StringUtils {

    /**
     * string是否为空
     */
    public static boolean isNull(String str){
        return ( str == null || str.isEmpty() );
    }

    /**
     * string是否不为空
     */
    public static boolean isNotNull(String str){
        return ! isNull(str);
    }

    /**
     * object -- string
     */
    public static String obj2String(Object object){
        return String.valueOf(object);
    }

    /**
     * string -- int
     */
    public static Integer string2Int(String str){
        return string2Int(str, null);
    }

    /**
     * string -- int
     */
    public static Integer string2Int(String str, Integer defValue){
        return string2Int(str, 10, defValue);
    }

    /**
     * string -- int
     */
    public static Integer string2Int(String str, int radix, Integer defValue){
        try {
            return Integer.parseInt(str, radix);
        }catch (Exception e){
            return defValue;
        }
    }

    /**
     * string -- long
     */
    public static Long string2Long(String str){
        return string2Long(str, null);
    }

    /**
     * string -- long
     */
    public static Long string2Long(String str, Long defValue){
        return string2Long(str, 10, defValue);
    }

    /**
     * string -- long
     */
    public static Long string2Long(String str, int radix, Long defValue){
        try {
            return Long.parseLong(str, radix);
        }catch (Exception e){
            return defValue;
        }
    }

    /**
     * string -- double
     */
    public static Double string2Double(String str){
        return string2Double(str, null);
    }

    /**
     * string -- double
     */
    public static Double string2Double(String str, Double defValue){
        try {
            return Double.parseDouble(str);
        }catch (Exception e){
            return defValue;
        }
    }

    /**
     * string -- float
     */
    public static Float string2Float(String str){
        return string2Float(str, null);
    }

    /**
     * string -- float
     */
    public static Float string2Float(String str, Float defValue){
        try {
            return Float.parseFloat(str);
        }catch (Exception e){
            return defValue;
        }
    }

}
