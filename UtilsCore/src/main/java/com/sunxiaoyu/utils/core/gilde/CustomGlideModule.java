package com.sunxiaoyu.utils.core.gilde;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

/**
 *
 * 自定义AppGlideModule
 *
 * 该类用kotlin写无法正常生成GlideApp子类
 * 而且其他kotlin类在调用GlideApp的时候编译不通过  （Unresolved reference: GlideApp）
 *
 * Created by sunxiaoyu on 2017/7/28.
 */
@GlideModule
public class CustomGlideModule extends AppGlideModule{

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        //设置磁盘缓冲保存路径为 context.getExternalCacheDir()/imageCache ,   设置磁盘缓冲最大为100M
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, "imageCache", 100 * 1024 * 1024));
        builder.setMemoryCache(new LruResourceCache(10 * 1024 * 1024));
        builder.setBitmapPool(new LruBitmapPool(10 * 1024 * 1024));     //内存10M
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
