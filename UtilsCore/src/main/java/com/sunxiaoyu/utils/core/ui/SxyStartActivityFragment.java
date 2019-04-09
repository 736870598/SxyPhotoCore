package com.sunxiaoyu.utils.core.ui;

import android.content.Intent;

import com.sunxiaoyu.utils.core.model.ActivityResultInfo;

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
        setResult(new ActivityResultInfo(resultCode, data));
    }
}
