package com.sunxiaoyu.utils.core.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 系统相关的工具类
 * Created by Administrator on 2018/1/10 0010.
 */
public class SystemUtils {

    /**
     * 复制到剪切板
     */
    public static boolean copyToClipBoard(Context context, String text){
        ClipData clipData = ClipData.newPlainText("text_copy", String.valueOf(text));
        ClipboardManager manager = (ClipboardManager) context.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null){
            manager.setPrimaryClip(clipData);
            return true;
        }
        return false;
    }

    /**
     * 显示软键盘
     * 要注意windowSoftInputMode为stateHidden和stateAlwaysVisible的区别
     */
    public static void showSoftKeyboard(View view){
        if (view != null){
            InputMethodManager inputMethodManager = (InputMethodManager) view.getContext()
                    .getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null){
                inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
            }
        }
    }

    /**
     * 关闭软键盘
     */
    public static void hideSoftKeyboard(View view){
        if (view != null){
            InputMethodManager inputMethodManager = (InputMethodManager) view.getContext()
                    .getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null){
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     * 获取屏幕尺寸
     */
    public static Point getScreenSize(Context context){
        DisplayMetrics metrics = context.getApplicationContext().getResources().getDisplayMetrics();
        return new Point(metrics.widthPixels, metrics.heightPixels);
    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, Float dpValue){
        float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, Float pxValue){
        float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }
}
