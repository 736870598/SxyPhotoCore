package com.sunxiaoyu.utils.core.model;

import android.app.Activity;
import android.content.Intent;

import com.sunxiaoyu.utils.core.PhotoConfig;

import java.io.Serializable;

/**
 *  Activity返回结果
 */
public class ActivityResultInfo implements Serializable {

    private int requestCode;
    private int resultCode;
    private Intent data;

    public ActivityResultInfo(int resultCode, Intent data) {
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

    public boolean resultIsOk(){
        return resultCode == Activity.RESULT_OK;
    }

    public boolean resultIsOkAndDataNotNull(){
        return resultIsOk() && data != null;
    }

    public String getPhotoPath(){
        if (resultIsOkAndDataNotNull()){
            return getData().getStringExtra(PhotoConfig.RESULT_PHOTO_PATH);
        }
        return null;
    }

    @Override
    public String toString() {
        return "ActivityResultInfo{" +
                "requestCode=" + requestCode +
                ", resultCode=" + resultCode +
                ", data=" + data +
                '}';
    }
}
