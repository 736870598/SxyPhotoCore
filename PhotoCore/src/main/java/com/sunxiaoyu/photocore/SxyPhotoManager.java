package com.sunxiaoyu.photocore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sunxiaoyu.photocore.core.SxySeePhotoActivity;
import com.sunxiaoyu.photocore.core.SxySelectPhotoActivity;
import com.sunxiaoyu.photocore.core.SxyTakePhotoAcivity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 选择图片， 拍照， 压缩图片， 查看图片
 *
 * Created by SunXiaoyu on 2017/12/11/011.
 */
public class SxyPhotoManager {

    private SxyPhotoManager(){}

    public static SxyPhotoManager getManager(){
        return SxyPhotoManagerHolder.instance;
    }

    private static class SxyPhotoManagerHolder{
           static SxyPhotoManager instance = new SxyPhotoManager();
    }


    /**
     * 选择照片
     * @param act           activity
     * @param requestCode   请求code
     * @param needCompress  是否需要压缩
     * @param savePath      压缩后保存路径
     */
    public void selectPhoto(Activity act, int requestCode, boolean needCompress, String savePath){
        Intent intent = new Intent(act, SxySelectPhotoActivity.class);
        intent.putExtra(PhotoConfig.NEED_COMPRESS, needCompress);
        intent.putExtra(PhotoConfig.SAVE_PATH, savePath);
        act.startActivityForResult(intent, requestCode);
    }

    /**
     * 拍照
     * @param act           activity
     * @param requestCode   请求code
     * @param needCompress  是否需要压缩
     * @param savePath      保存路径
     */
    public void takePhoto(Activity act, int requestCode, boolean needCompress, String savePath){
        Intent intent = new Intent(act, SxyTakePhotoAcivity.class);
        intent.putExtra(PhotoConfig.NEED_COMPRESS, needCompress);
        intent.putExtra(PhotoConfig.SAVE_PATH, savePath);
        act.startActivityForResult(intent, requestCode);
    }


    /**
     * 查看图片
     * @param context   上下文
     * @param pathList  路径集合
     * @param seePos    查看图片位置
     */
    public void seePhoto(Context context, ArrayList<String> pathList, int seePos){
        Intent intent = new Intent(context, SxySeePhotoActivity.class);
        intent.putExtra(PhotoConfig.ONE_PHOTO, false);
        intent.putStringArrayListExtra(PhotoConfig.PHOTO_PATH_LIST, pathList);
        intent.putExtra(PhotoConfig.SEE_POS, seePos);
        context.startActivity(intent);
    }

    /**
     * 查看图片
     * @param context   上下文
     * @param path      路径
     */
    public void seePhoto(Context context, String path){
        Intent intent = new Intent(context, SxySeePhotoActivity.class);
        intent.putExtra(PhotoConfig.ONE_PHOTO, true);
        intent.putExtra(PhotoConfig.PHOTO_PATH, path);
        context.startActivity(intent);
    }


    /**
     * 压缩图片
     * @param bitmapPath    图片路径
     * @param savePath      保存路径
     * @param quality       压缩质量（默认30%）
     */
    public void compress(String bitmapPath, String savePath, int quality){
        compress(BitmapFactory.decodeFile(bitmapPath), savePath, quality);
    }

    /**
     * 压缩图片
     * @param bitmap    图片
     * @param savePath  保存路径
     * @param quality   压缩质量（默认30%）
     */
    public void compress(Bitmap bitmap, String savePath, int quality){

    }

}
