package com.sunxiaoyu.utils.core.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;


import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;

/**
 * 申请权限
 * Created by Sunxy on 2018/1/8.
 */
public class SxyPermissionsFragment extends SxyBaseFragment {

    private ArrayList<String> list;
    private PublishSubject<Integer> mSubject;
    private AlertDialog dialog;
    private Disposable disposable;

    public SxyPermissionsFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        Observable.just("onStart")
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return (disposable != null && !disposable.isDisposed());
                    }
                })
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return !(dialog != null && dialog.isShowing());
                    }
                })
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return (list != null && !list.isEmpty());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        applyPermissions();
                    }
                });
    }

    @Override
    public Observable<Integer> requestPermissions(final String...permissions){
        if(mSubject != null) mSubject.onComplete();
        mSubject = PublishSubject.create();

        return mSubject.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable dis) throws Exception {
                disposable = dis;
                doSomethingStart(permissions);
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 申请权限
     */
    private void applyPermissions(){
        if (list.isEmpty()){
            mSubject.onNext(PackageManager.PERMISSION_GRANTED);
            onComplete();
        }else{
            String[] deniedPermissions = new String[list.size()];
            list.toArray(deniedPermissions);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
                mSubject.onNext(PackageManager.PERMISSION_GRANTED);
                onComplete();
            }else{
                requestPermissions(deniedPermissions, 100);
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != 100){
            mSubject.onNext(PackageManager.PERMISSION_DENIED);
            onComplete();
            return ;
        }

        int size = Math.min(permissions.length, grantResults.length);
        for (int i = 0; i < size; i++){
            if (grantResults[i] == PackageManager.PERMISSION_DENIED){
                boolean canToast = shouldShowRequestPermissionRationale(permissions[i]);
                //拒绝了该权限
                if (canToast){
                    //可以继续申请
                    applyPermissions();
                }else{
                    //不再提醒
                    showDialog2Toast();
                }
                return;
            }else{
                //这个权限有的
                list.remove(permissions[i]);
            }
        }

        mSubject.onNext(PackageManager.PERMISSION_GRANTED);
        onComplete();
    }

    /**
     * 弹出dialog提示必须开启权限才能使用
     */
    private void showDialog2Toast(){
        if (dialog != null && dialog.isShowing()){
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("提示")
                .setMessage("缺少使用该功能的必要权限，请前往开启")
                .setCancelable(false)
                .setNegativeButton("不用了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSubject.onNext(PackageManager.PERMISSION_DENIED);
                        onComplete();
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("前往开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                        dialog.dismiss();
                    }
                });
        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    /**
     * 跳转到该app设置界面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
        getActivity().startActivity(intent);
    }


    private void doSomethingStart(String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            mSubject.onNext(PackageManager.PERMISSION_GRANTED);
            mSubject.onComplete();
            return;
        }

        list = new ArrayList<>(permissions.length);
        Observable.fromArray(permissions)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String permission) throws Exception {
                        return ContextCompat.checkSelfPermission(getActivity(), permission) == PackageManager.PERMISSION_DENIED;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String permission) throws Exception {
                        list.add(permission);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        applyPermissions();
                    }
                });
    }


    private void onComplete(){
        if (mSubject != null){
            mSubject.onComplete();
        }
        if (disposable != null){
            disposable.dispose();
        }
    }

    @Override
    protected void doSomethingStart(Intent data) {
    }

    @Override
    protected void doSomethingResult(int requestCode, int resultCode, Intent data) {
    }
}
