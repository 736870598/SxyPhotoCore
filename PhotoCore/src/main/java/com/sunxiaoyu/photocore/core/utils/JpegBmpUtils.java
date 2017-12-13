package com.sunxiaoyu.photocore.core.utils;

import android.graphics.Bitmap;

/**
 *  压缩图片类
 */
public class JpegBmpUtils {

    /**
     * 压缩图片
     * @param bmp           图片
     * @param savePath      保持路径
     * @param quality       压缩比例（0-100）
     * @return   处理结果
     */
    public static String compressBmp(Bitmap bmp, String savePath, int quality){
        return nativeCompressBmp(bmp, bmp.getWidth(), bmp.getHeight(), quality, savePath);
    }



    public static native String nativeCompressBmp(Bitmap bmp, int w, int h, int quality, String path);

    static {
        System.loadLibrary("jpeg");
        System.loadLibrary("jpeg_sxy");
    }



}
