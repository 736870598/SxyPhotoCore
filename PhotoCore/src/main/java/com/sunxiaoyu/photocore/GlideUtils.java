package com.sunxiaoyu.photocore;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sunxiaoyu.photocore.core.gilde.GlideApp;

/**
 * http://blog.csdn.net/yulyu/article/details/55096713?fps=1&locationNum=2
 * http://blog.csdn.net/fancylovejava/article/details/44747759
 *
 * Created by sunxiaoyu on 2017/7/28.
 */

public class GlideUtils {

    /**
     *  加载图片
     *  @param fragment Fragment
     */
    public static void loadImage(Fragment fragment, String url, ImageView imageView){
        //清除之前imageView上的缓存图片（由于RecycleView的缓冲导致可能加载错乱）
        GlideApp.with(fragment).clear(imageView);
        //加载图片
        GlideApp.with(fragment).asBitmap().load(url).apply(getCommOptions()).into(imageView);
    }

    /**
     *  加载图片
     * @param activity  Activity
     */
    public static void loadImage(Activity activity, String url, ImageView imageView){
        //清除之前imageView上的缓存图片（由于RecycleView的缓冲导致可能加载错乱）
        GlideApp.with(activity).clear(imageView);
        //加载图片
        GlideApp.with(activity).asBitmap().load(url).apply(getCommOptions()).into(imageView);
    }

    /**
     *  加载图片
     *  @param context Context
     */
    public static void loadImage(Context context, String url, int w, int h, ImageView imageView){
        //清除之前imageView上的缓存图片（由于RecycleView的缓冲导致可能加载错乱）
        GlideApp.with(context).clear(imageView);
        //加载图片
        GlideApp.with(context).asBitmap().load(url).apply(getCommOptions()).override(w,h).into(imageView);
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
