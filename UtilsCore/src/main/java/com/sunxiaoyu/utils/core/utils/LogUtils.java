package com.sunxiaoyu.utils.core.utils;

import android.util.Log;

import com.sunxiaoyu.utils.BuildConfig;

/**
 * 显示Log
 * Created by Sunxy on 2018/1/11.
 */
public class LogUtils {

    private static String TAG = "sunxy";
    private static boolean isDebug = false;

    public static void setTag(String tag){
        TAG = tag;
    }

    public static void v(String log){
        if (isDebug || BuildConfig.DEBUG){
            v(TAG, log);
        }
    }

    public static void v(String tag, String log){
        Log.v(tag, log);
    }

    public static void e(String log){
        if (isDebug || BuildConfig.DEBUG){
            e(TAG, log);
        }
    }

    public static void e(String tag, String log){
        Log.e(tag, log);
    }

}
