package com.sunxiaoyu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

import com.sunxiaoyu.utils.core.ActivityResult;
import com.sunxiaoyu.utils.core.PhotoConfig;
import com.sunxiaoyu.utils.core.model.ActivityResultInfo;
import com.sunxiaoyu.utils.core.model.TypeEnum;
import com.sunxiaoyu.utils.core.ui.SxySeePhotoActivity;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 *
 * 选择图片, 拍照, 压缩图片, 查看图片, 加载图片, 申请权限
 *
 * 显示图片使用的是Gilde框架，默认图片缓存在 context.getExternalCacheDir("imageCache")
 * 文件夹下，请提前声明好
 *
 * Created by SunXiaoyu on 2017/12/11/011.
 */
public class UtilsCore {

    private UtilsCore(){}

    public static UtilsCore manager(){
        return SxyPhotoManagerHolder.instance;
    }

    private static class SxyPhotoManagerHolder{
        static UtilsCore instance = new UtilsCore();
    }


    /**
     * 选择照片
     * @param act           activity
     * @param requestCode   请求code
     * @return  RxJava使用Observable
     */
    public Observable<ActivityResultInfo> selectPicture(Activity act, int requestCode){
        Intent intent = new Intent();
        return startForResult(act, requestCode, TypeEnum.TYPE_SELECT_PICTURE, intent);
    }

    /**
     * 拍照
     * @param act           activity
     * @param requestCode   请求code
     * @param savePath      压缩后保存路径
     * @param callback      照片选择后回调函数
     */
    public void takePicture(Activity act, int requestCode, String savePath,  ActivityResult.Callback callback){
        Intent intent = new Intent();
        intent.putExtra(PhotoConfig.SAVE_PATH, savePath);
        startForResult(act, requestCode, TypeEnum.TYPE_TAKE_PICTURE, intent, callback);
    }

    /**
     * 拍照
     * @param act           activity
     * @param requestCode   请求code
     * @param savePath      压缩后保存路径
     * @return  RxJava使用Observable
     */
    public Observable<ActivityResultInfo> takePicture(Activity act, int requestCode,  String savePath){
        Intent intent = new Intent();
        intent.putExtra(PhotoConfig.SAVE_PATH, savePath);
        return startForResult(act, requestCode, TypeEnum.TYPE_TAKE_PICTURE, intent);
    }

    /**
     * 申请权限 （直到用户做出最终选择。 《同意》 或 《点击了不再询问并拒绝了，而且还不去设置界面设置》）
     * @param act           activity
     * @param permissions   请求的权限
     * @return  RxJava使用Observable   true 有权限  false 没有权限
     */
    public Observable<Boolean> requestPermissions(Activity act, String...permissions){
        return new ActivityResult(act, TypeEnum.TYPE_APPLY_PERMISSION).requestPermissions(permissions);
    }

    /**
     * 跳转activity （封装 onActivityForResult ）
     * @param act           activity
     * @param requestCode   请求code
     * @param intent        跳转intent
     * @param callback      回调函数
     */
    public void startForResult(Activity act, int requestCode, Intent intent, ActivityResult.Callback callback){
        startForResult(act, requestCode, TypeEnum.TYPE_START_ACTIVITY, intent, callback);
    }

    /**
     * 跳转activity （封装 onActivityForResult ）
     * @param act           activity
     * @param requestCode   请求code
     * @param intent        跳转intent
     * @return  RxJava使用Observable
     */
    public Observable<ActivityResultInfo> startForResult(Activity act, int requestCode, Intent intent){
        return  startForResult(act, requestCode, TypeEnum.TYPE_START_ACTIVITY, intent);
    }

    /**
     * 查看图片
     * @param context   上下文
     * @param pathList  路径集合
     * @param seePos    查看图片位置
     */
    public void seePhoto(Context context, ArrayList<String> pathList, int seePos){
        seePhoto(context, pathList, seePos, context.getResources().getConfiguration().orientation);
    }

    /**
     * 查看图片
     * @param context   上下文
     * @param pathList  路径集合
     * @param seePos    查看图片位置
     * @param screenOrientation   屏幕方向
     * ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE 横屏
     * ActivityInfo.SCREEN_ORIENTATION_PORTRAIT  竖屏
     */
    public void seePhoto(Context context, ArrayList<String> pathList, int seePos, int screenOrientation){
        Intent intent = new Intent(context, SxySeePhotoActivity.class);
        intent.putExtra(PhotoConfig.ONE_PHOTO, false);
        intent.putStringArrayListExtra(PhotoConfig.PHOTO_PATH_LIST, pathList);
        intent.putExtra(PhotoConfig.SEE_POS, seePos);
        intent.putExtra(PhotoConfig.SCREEN_ORIENTATION, screenOrientation);
        context.startActivity(intent);
    }

    /**
     * 查看图片
     * @param context   上下文
     * @param path      路径
     */
    public void seePhoto(Context context, String path){
        seePhoto(context, path, context.getResources().getConfiguration().orientation);
    }

    /**
     * 查看图片
     * @param context   上下文
     * @param path      路径
     * @param screenOrientation   屏幕方向
     * ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE 横屏
     * ActivityInfo.SCREEN_ORIENTATION_PORTRAIT  竖屏
     */
    public void seePhoto(Context context, String path, int screenOrientation){
        Intent intent = new Intent(context, SxySeePhotoActivity.class);
        intent.putExtra(PhotoConfig.ONE_PHOTO, true);
        intent.putExtra(PhotoConfig.PHOTO_PATH, path);
        intent.putExtra(PhotoConfig.SCREEN_ORIENTATION, screenOrientation);
        context.startActivity(intent);
    }


    /**
     * 跳转activity （封装 onActivityForResult ）
     * @param act           activity
     * @param requestCode   请求code
     * @param typeEnum      请求类型
     * @param intent        跳转intent
     * @param callback      回调函数
     */
    private void startForResult(Activity act, int requestCode, TypeEnum typeEnum, Intent intent, ActivityResult.Callback callback){
        new ActivityResult(act, typeEnum).startForResult(intent, requestCode, callback);
    }

    /**
     * 跳转activity （封装 onActivityForResult ）
     * @param act           activity
     * @param requestCode   请求code
     * @param typeEnum      请求类型
     * @param intent        跳转intent
     * @return  RxJava使用Observable
     */
    private Observable<ActivityResultInfo> startForResult(Activity act, int requestCode, TypeEnum typeEnum, Intent intent){
        return new ActivityResult(act, typeEnum).startForResult(intent, requestCode);
    }

}
