package com.sunxiaoyu.photocore.core;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;


import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 *
 * 权限请求类
 * 通过RxPermissions实现
 * Created by SunXiaoyu on 2017/12/12/012.
 */
public class RxPermissionsManager {

    private Activity activity;
    private PermissionDealListener listener;
    private String[] permissions;

    private RxPermissionsManager(){}

    public static RxPermissionsManager getManager(){
        return RxPermissionsManagerHolder.instance;
    }

    private static class RxPermissionsManagerHolder{
        static RxPermissionsManager instance = new RxPermissionsManager();
    }

    /**
     *  请求权限
     *  SxyUtilsManager.getManager().requestPermissions 方法
     *
     * @param activity      activity
     * @param listener      权限处理结果，未授权会重复请求，授权通过该回调传出
     * @param permissions   申请的权限 ps: Manifest.permission.WRITE_EXTERNAL_STORAGE
     */
    public void requestPermissions(Activity activity, PermissionDealListener listener, String...permissions){
        this.activity = activity;
        this.listener = listener;
        this.permissions = permissions;
        requestPermissions(permissions);
    }

    /**
     * 请求权限
     * @param permissions 要请求的权限
     */
    private void requestPermissions(String...permissions){
        for (String permission : permissions){
            if (permissionIsDenied(permission)){
                new RxPermissions(activity)
                        .requestEach(permissions)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) throws Exception {
                                dealPermission(permission);
                            }
                        });
                return;
            }
        }

        if (listener != null){
            listener.havePermission();
        }
        activity = null;
        listener = null;

    }

    /**
     * 权限是否被拒绝
     */
    private boolean permissionIsDenied(String permission) {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED;
    }

    /**
     * 处理授权结果
     */
    private void dealPermission(Permission permission) {
        if (!permission.granted) {   //不同意授权
            if (permission.shouldShowRequestPermissionRationale) {
                //可以询问
                requestPermissions(permission.name);
            } else {
                //不让询问
                showDialog2Toast();
            }
        }else{
            //同意了，从此再走下
            requestPermissions(permissions);
        }
    }

    /**
     * 弹出dialog提示必须开启权限才能使用
     */
    private void showDialog2Toast(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                .setTitle("提示")
                .setMessage("缺少使用该功能的必要权限，请前往开启")
                .setCancelable(false)
                .setPositiveButton("前往开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startAppSettings();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        activity = null;
        listener = null;
    }

    /**
     * 跳转到该app设置界面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        activity.startActivity(intent);
        activity = null;
        listener = null;
    }

    public interface PermissionDealListener{
        public void havePermission();
    }

}
