package com.sunxiaoyu.photocore.core.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 *
 * 版本消息工具类
 *
 * Created by sunxiaoyu
 */
public class VersionUtils {

    /**
     * 得到工程版本号
     */
    public static int projectVersionCode(Context context){
        try {
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            return packInfo.versionCode;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 得到工程版本消息
     */
    public static String  projectVersion(Context context){
        try {
            PackageManager packageManager = context.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            return packInfo.versionName;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
            return "未知版本";
        }
    }

    /**
     * 获取系统android版本号
     *
     *  Build.VERSION_CODES 中保存着所有版本号
     *
     * @return 当前android版本号
     */
    public static int androidVersion(){
        return Build.VERSION.SDK_INT;
    }

}
