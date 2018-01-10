package com.sunxiaoyu.utils.core.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences的一个工具类
 */
public class SharedPreferencesUtils {


    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "utilsCore_spFile";

    /**
     * 保存数据的方法，需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     */
    public static void setParam(Context context , String key, Object object){

        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if("String".equals(type)){
            editor.putString(key, (String)object);
        }
        else if("Integer".equals(type)){
            editor.putInt(key, (Integer)object);
        }
        else if("Boolean".equals(type)){
            editor.putBoolean(key, (Boolean)object);
        }
        else if("Float".equals(type)){
            editor.putFloat(key, (Float)object);
        }
        else if("Long".equals(type)){
            editor.putLong(key, (Long)object);
        }

        editor.apply();
    }

    /**
     * 得到保存数据的方法，根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     */
    public static Object getParam(Context context , String key, Object defaultValue){
        String type = defaultValue.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if("String".equals(type)){
            return sp.getString(key, (String)defaultValue);
        }
        else if("Integer".equals(type)){
            return sp.getInt(key, (Integer)defaultValue);
        }
        else if("Boolean".equals(type)){
            return sp.getBoolean(key, (Boolean)defaultValue);
        }
        else if("Float".equals(type)){
            return sp.getFloat(key, (Float)defaultValue);
        }
        else if("Long".equals(type)){
            return sp.getLong(key, (Long)defaultValue);
        }
        return null;
    }
}
