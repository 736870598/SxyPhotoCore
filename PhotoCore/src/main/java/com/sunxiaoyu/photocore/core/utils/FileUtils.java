package com.sunxiaoyu.photocore.core.utils;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 *
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

    public static String getImagePathFromUri(Context context, Uri uri){
        if(!uri.toString().startsWith("content://")){
            return uri.getPath();
        }
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null,null,null);
            if (cursor == null || cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            return cursor.getString(columnIndex);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        return null;
    }
}
