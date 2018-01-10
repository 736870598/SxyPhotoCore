package com.sunxiaoyu.utils.core.utils;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件工具类
 * Created by SunXiaoyu on 2017/12/12/012.
 */
public class FileUtils {

    /**
     * string 转 uri
     */
    public static Uri path2Uri(Context context, String path){
        if (path == null){
            return null;
        }
        return File2Uri(context, new File(path));
    }

    /**
     * file 转 uri
     */
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

    /**
     *
     * @param context   上下文
     * @param path      文件路径
     * @param name      文件名称
     * @param writeStr  写入字符串
     * @param isCover   是否覆盖写入
     */
    public static void writeFile(Context context, String path, String name, String writeStr, boolean isCover){
        writeFile(context, createFile(path, name), writeStr, isCover);
    }

    /**
     * 写文件
     * @param context   上下文
     * @param file      文件
     * @param writeStr  写入字符串
     * @param isCover   是否覆盖写入
     */
    public static void writeFile(Context context, File file, String writeStr, boolean isCover){
        if (file == null || writeStr == null){
            return;
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = context.openFileOutput(file.getAbsolutePath(), isCover ? Context.MODE_PRIVATE : Context.MODE_APPEND);
            outputStream.write(writeStr.getBytes("utf-8"));
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读文件
     * @param context   上下文
     * @param path      读取文件路径
     * @param name      读取文件名字
     * @return 文件内容 utf-8
     */
    public static String readFile(Context context, String path, String name){
        return readFile(context, new File(path, name));
    }

    /**
     * 读文件
     * @param context   上下文
     * @param file      读取文件
     * @return 文件内容 utf-8
     */
    public static String readFile(Context context, File file){
        if (!file.exists()){
            return null;
        }

        FileInputStream inputStream = null;
        try {
            inputStream = context.openFileInput(file.getAbsolutePath());
            byte temp[] = new byte[1024];
            StringBuilder sb = new StringBuilder("");
            int len;
            while ((len = inputStream.read(temp)) > 0){
                sb.append(new String(temp, 0, len, "utf-8"));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 创建文件
     */
    public static void createFile(File file){
        if (file != null && !file.exists())
            file.mkdirs();
    }


    /**
     * 创建文件
     */
    public static void createFile(String filePath){
        createFile(new File(filePath));
    }

    /**
     * 创建文件
     */
    public static File createFile(String filePath, String name){
        File file = new File(filePath, name);
        if (!file.exists()){
            createFile(file.getParentFile());
            try {
                file.createNewFile();
                return file;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 删除文件
     */
    public static void delFile(String path){
        delFile(new File(path));
    }

    /**
     * 删除文件
     */
    public static void delFile(File file){
        if (file.exists()){
            if (file.isFile()){
                file.delete();
            }else{
                File[] listFiles = file.listFiles();
                for (File file1: listFiles){
                    delFile(file1);
                }
            }
        }
    }

    /**
     * 图片uri转string
     */
    public static String getImagePathFromUri(Context context, Uri uri){
        String path = null;
        if (ContentResolver.SCHEME_FILE.equals(uri.getScheme())) {
            // 以 file:// 开头的
            path = uri.getPath();
        }else if(ContentResolver.SCHEME_CONTENT.equals(uri.getScheme())){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)){
                // 4.4及之后的有些是以 content:// 开头的，比如 content://com.android.providers.media.documents/document/image%3A235700
                if (isExternalStorageDocument(uri)) {
                    // ExternalStorageProvider
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];
                    if ("primary".equalsIgnoreCase(type)) {
                        path = Environment.getExternalStorageDirectory() + "/" + split[1];
                        return path;
                    }
                } else if (isDownloadsDocument(uri)) {
                    // DownloadsProvider
                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                            Long.valueOf(id));
                    path = getDataColumn(context, contentUri, null, null);
                    return path;
                } else if (isMediaDocument(uri)) {
                    // MediaProvider
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];
                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[]{split[1]};
                    path = getDataColumn(context, contentUri, selection, selectionArgs);
                    return path;
                }
            }else{
                // 以 content:// 开头的，比如 content://media/extenral/images/media/17766
                Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        if (columnIndex > -1) {
                            path = cursor.getString(columnIndex);
                        }
                    }
                    cursor.close();
                }
            }
        }
        return path;
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

}
