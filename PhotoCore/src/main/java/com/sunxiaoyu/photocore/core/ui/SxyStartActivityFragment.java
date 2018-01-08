package com.sunxiaoyu.photocore.core.ui;

import android.app.Activity;
import android.content.Intent;

import com.sunxiaoyu.photocore.core.model.ActivityResultInfo;

/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class SxyStartActivityFragment extends SxyBaseFragment {

    private static final int START_ACTIVITY = 102;

    @Override
    protected void doSomethingStart(Intent data) {
        startActivityForResult(data, START_ACTIVITY);
    }

    @Override
    protected void doSomethingResult(int requestCode, int resultCode, Intent data) {
        if (START_ACTIVITY == requestCode && resultCode == Activity.RESULT_OK){
            setResult(new ActivityResultInfo(data), true);
        }else{
            setResult(new ActivityResultInfo(2, data),true);
        }
    }
}
