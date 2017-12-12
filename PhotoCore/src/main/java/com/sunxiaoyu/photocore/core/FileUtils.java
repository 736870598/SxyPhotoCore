package com.sunxiaoyu.photocore.core;


import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * Created by SunXiaoyu on 2017/12/12/012.
 */

public class FileUtils {

    public static Uri path2Uri(Context context, String path){
        if (path == null){
            return null;
        }
        return File2Uri(context, new File(path));
    }

    public static Uri File2Uri(Context context, File file){
        if (file == null){
            return null;
        }
        createFile(file.getParentFile());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return FileProvider.getUriForFile(context.getApplicationContext(),
                    context.getPackageName()+".fileprovider", file);
        } else{
            return Uri.fromFile(file);
        }
    }

    public static void createFile(File file){
        if (file != null && !file.exists())
            file.mkdirs();
    }
}
