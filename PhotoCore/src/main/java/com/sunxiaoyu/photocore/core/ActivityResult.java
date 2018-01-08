package com.sunxiaoyu.photocore.core;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;

import com.sunxiaoyu.photocore.core.model.ActivityResultInfo;
import com.sunxiaoyu.photocore.core.model.TypeEnum;
import com.sunxiaoyu.photocore.core.ui.SxyBaseFragment;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class ActivityResult {

    private SxyBaseFragment fragment;

    public ActivityResult(Activity activity, TypeEnum typeEnum) {
        fragment = SxyBaseFragment.getFragment(activity, typeEnum);
    }

    public ActivityResult(Fragment fragment, TypeEnum typeEnum){
        this(fragment.getActivity(), typeEnum);
    }

    public Observable<ActivityResultInfo> startForResult(Intent intent, int requestCode) {
        return fragment.startForResult(intent, requestCode);
    }

    public Observable<Integer> requestPermissions(String...permissions) {
        return fragment.requestPermissions(permissions);
    }

    public void startForResult(Intent intent, int requestCode, Callback callback) {
        fragment.startForResult(intent, requestCode, callback);
    }

    public interface Callback {
        void onActivityResult(ActivityResultInfo info);
    }
}
