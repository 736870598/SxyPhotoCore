package com.sunxiaoyu.utils.core;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sunxiaoyu.utils.R;
import com.sunxiaoyu.utils.core.gilde.GlideApp;

/**
 * http://blog.csdn.net/yulyu/article/details/55096713?fps=1&locationNum=2
 * http://blog.csdn.net/fancylovejava/article/details/44747759
 *
 * Created by sunxiaoyu on 2017/7/28.
 */

public class GlideUtils {

    /**
     * 加载图片
     * @param context       上下文
     * @param url           图片地址
     * @param imageView     imageView
     */
    public static void loadImage(Context context, String url, ImageView imageView){
        //清除之前imageView上的缓存图片（由于RecycleView的缓冲导致可能加载错乱）
        GlideApp.with(context).clear(imageView);
        //加载图片
        GlideApp.with(context).asBitmap().load(url).apply(getCommOptions()).fitCenter().into(imageView);
    }


    /**
     * 加载图片
     * @param context       上下文
     * @param url           图片地址
     * @param w             图片显示宽度
     * @param h             图片显示高度
     * @param imageView     imageView
     */
    public static void loadImage(Context context, String url, int w, int h, ImageView imageView){
        //清除之前imageView上的缓存图片（由于RecycleView的缓冲导致可能加载错乱）
        GlideApp.with(context).clear(imageView);
        //加载图片
        GlideApp.with(context).asBitmap().load(url).apply(getCommOptions()).override(w,h).fitCenter().into(imageView);
    }


    private static RequestOptions commOptions;

    private static RequestOptions getCommOptions(){
        if (commOptions == null){
            commOptions = new RequestOptions()
                    .centerInside()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)   //保存所有图片
                    .fallback(R.mipmap.icon_error)              //url为空时显示图片
                    .error(R.mipmap.icon_error)                 //加载失败是显示图片
            ;
        }
        return commOptions;
    }

}
