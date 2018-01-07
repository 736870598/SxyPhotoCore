package com.sunxiaoyu.photocore;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;

import com.sunxiaoyu.photocore.core.model.ActivityResultInfo;
import com.sunxiaoyu.photocore.core.ui.SxySelectPhotoFragment;

import io.reactivex.Observable;

/**
 *  将activity的activityOnResult方法抽取出来
 * 参照 https://github.com/AnotherJack/AvoidOnResult
 */
public class AvoidOnResult {
    private static final String TAG = "AvoidOnResult";
    private SxySelectPhotoFragment mAvoidOnResultFragment;
//    private AvoidOnResultFragment mAvoidOnResultFragment;

    public AvoidOnResult(Activity activity) {
        mAvoidOnResultFragment = getAvoidOnResultFragment(activity);
    }

    public AvoidOnResult(Fragment fragment){
        this(fragment.getActivity());
    }

    private SxySelectPhotoFragment getAvoidOnResultFragment(Activity activity) {
        SxySelectPhotoFragment avoidOnResultFragment = findAvoidOnResultFragment(activity);
        if (avoidOnResultFragment == null) {
            avoidOnResultFragment = new SxySelectPhotoFragment();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(avoidOnResultFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return avoidOnResultFragment;
    }

    private SxySelectPhotoFragment findAvoidOnResultFragment(Activity activity) {
        return (SxySelectPhotoFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }

    public Observable<ActivityResultInfo> startForResult(Intent intent, int requestCode) {
        return mAvoidOnResultFragment.startForResult(intent, requestCode);
    }

    public Observable<ActivityResultInfo> startForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(mAvoidOnResultFragment.getActivity(), clazz);
        return startForResult(intent, requestCode);
    }

    public void startForResult(Intent intent, int requestCode, Callback callback) {
        mAvoidOnResultFragment.startForResult(intent, requestCode, callback);
    }

    public void startForResult(Class<?> clazz, int requestCode, Callback callback) {
        Intent intent = new Intent(mAvoidOnResultFragment.getActivity(), clazz);
        startForResult(intent, requestCode, callback);
    }

    public interface Callback {
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
