package com.sunxiaoyu.photocore.core.model;

import android.content.Intent;

/**
 */
public class ActivityResultInfo {

    //状态吗：0 完成  1 处理中  2 取消了  3 出错了
    private int state;
    //错误信息 当状态码为 3 时有值
    private String errorMsg;
    //返回的data
    private Intent data;
    //请求码
    private int requestCode;

    public ActivityResultInfo(Intent data) {
        this.state = 0;
        this.data = data;
    }

    public ActivityResultInfo(int state, Intent data) {
        this.state = state;
        this.data = data;
    }

    public ActivityResultInfo(String errorMsg) {
        this.state =3;
        this.errorMsg = errorMsg;
    }

    public int getState() {
        return state;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Intent getData() {
        return data;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public boolean success(){
        return state == 0;
    }

    @Override
    public String toString() {
        return "ActivityResultInfo{" +
                "state=" + state +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                ", requestCode=" + requestCode +
                '}';
    }
}
