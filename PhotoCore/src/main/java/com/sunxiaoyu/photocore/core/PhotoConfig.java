package com.sunxiaoyu.photocore.core;

/**
 * config
 * Created by SunXiaoyu on 2017/12/11/011.
 */
public class PhotoConfig {

    /**
     * 本module中使用
     */
    //是否需要压缩
    public static final String NEED_COMPRESS = "needCompress";
    //保存路径
    public static final String SAVE_PATH = "savePath";
    //是否查看单张照片
    public static final String ONE_PHOTO = "isOnePhoto";
    //查看单张照片路径
    public static final String PHOTO_PATH = "photoPath";
    //查看照片集合
    public static final String PHOTO_PATH_LIST = "photoPathList";
    //查看照片位置
    public static final String SEE_POS = "seePos";

    /**
     * 返回给调用者使用，拍照或选取后图片的保存路径
     */
    public static final String RESULT_PHOTO_PATH = "resultPhotoPath";

}
