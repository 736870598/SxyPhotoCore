package com.sunxiaoyu.photocore.core.activityresult;

import android.content.Intent;

/**
 *  将activity的activityOnResult方法抽取出来
 * 参照 https://github.com/AnotherJack/AvoidOnResult
 */
public class ActivityResultInfo {
    private int requestCode;
    private int resultCode;
    private Intent data;

    public ActivityResultInfo(int requestCode, int resultCode, Intent data) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Intent getData() {

        return data;
    }

    public void setData(Intent data) {
        this.data = data;
    }
}
