package com.sunxiaoyu.utils.core.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * -
 * <p>
 * Created by Sunxy on 2019/3/17.
 */
public class ImageUtils {

    public static void loadImage(Context context, ImageView photoView, String photoPath){
        Glide.with(context).load(photoPath).into(photoView);
    }

    public static void loadImage(Fragment fragment, ImageView photoView, String photoPath){
        Glide.with(fragment).load(photoPath).into(photoView);
    }

    public static void loadImage(android.app.Fragment fragment, ImageView photoView, String photoPath){
        Glide.with(fragment).load(photoPath).into(photoView);
    }

    public static void loadImage(Activity activity, ImageView photoView, String photoPath){
        Glide.with(activity).load(photoPath).into(photoView);
    }
}
