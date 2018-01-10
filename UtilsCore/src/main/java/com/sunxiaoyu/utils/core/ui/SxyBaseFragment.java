package com.sunxiaoyu.utils.core.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;


import com.sunxiaoyu.utils.core.ActivityResult;
import com.sunxiaoyu.utils.core.model.ActivityResultInfo;
import com.sunxiaoyu.utils.core.model.TypeEnum;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * baseFragment
 * Created by Administrator on 2018/1/8 0008.
 */

public abstract class SxyBaseFragment extends Fragment {

    private PublishSubject<ActivityResultInfo> mSubject;
    private ActivityResult.Callback mCallback;
    private boolean isCallback;
    private int requestCode;

    public SxyBaseFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public Observable<ActivityResultInfo> startForResult(final Intent intent, int requestCode) {
        this.requestCode = requestCode;
        if(mSubject != null) mSubject.onComplete();
        mSubject = PublishSubject.create();
        isCallback = false;
        return mSubject.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                doSomethingStart(intent);
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    public void startForResult(Intent intent, int requestCode, ActivityResult.Callback callback) {
        this.requestCode = requestCode;
        if (mCallback != null) mCallback = null;
        this.mCallback = callback;
        isCallback = true;
        doSomethingStart(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        doSomethingResult(requestCode, resultCode, data);
    }


    protected void setResult(ActivityResultInfo info, boolean isFinish){
        if (info == null){
            return;
        }else{
            info.setRequestCode(requestCode);
        }
        if (isCallback){
            if (mCallback != null) {
                mCallback.onActivityResult(info);
            }
        }else{
            if (mSubject != null){
                mSubject.onNext(info);
            }
        }
        if (isFinish){
            finish();
        }
    }

    @Override
    public void onDestroy() {
        finish();
        super.onDestroy();

    }

    private void finish(){
        if (mSubject != null){
            mSubject.onComplete();
        }
        mSubject = null;
        mCallback = null;
    }

    public Observable<Boolean> requestPermissions(final String...permissions){
        return null;
    }

    protected abstract void doSomethingStart(Intent data);
    protected abstract void doSomethingResult(int requestCode, int resultCode, Intent data);



    public static SxyBaseFragment getFragment(Activity activity, TypeEnum type){
        SxyBaseFragment fragment = (SxyBaseFragment) activity.getFragmentManager().findFragmentByTag(type.toString());
        if (fragment == null){
            fragment = generateFragment(type);
            if (fragment != null){
                FragmentManager fragmentManager = activity.getFragmentManager();
                fragmentManager.beginTransaction().add(fragment, type.toString()).commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
            }
        }
        return fragment;
    }

    private static SxyBaseFragment generateFragment(TypeEnum type){
        switch (type){
            case TYPE_SELECT_PICTURE:
                return new SxySelectPictureFragment();
            case TYPE_TAKE_PICTURE:
                return new SxyTakePictureFragment();
            case TYPE_START_ACTIVITY:
                return new SxyStartActivityFragment();
            case TYPE_APPLY_PERMISSION:
                return new SxyPermissionsFragment();
            default:
                return null;
        }
    }
}
