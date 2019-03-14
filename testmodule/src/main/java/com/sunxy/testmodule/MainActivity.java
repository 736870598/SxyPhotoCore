package com.sunxy.testmodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sunxiaoyu.utils.UtilsCore;
import com.sunxiaoyu.utils.core.PhotoConfig;
import com.sunxiaoyu.utils.core.model.ActivityResultInfo;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBackBtnClick(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        Disposable subscribe = UtilsCore.manager().startForResult(this, 12, intent)
                .subscribe(new Consumer<ActivityResultInfo>() {
                    @Override
                    public void accept(ActivityResultInfo activityResultInfo) throws Exception {
                        Log.v("sun--", activityResultInfo.toString());
                    }
                });
    }

    public void onSelectPhotoClick(View view){
        UtilsCore.manager().selectPicture(this, 100)
                .subscribe(new Consumer<ActivityResultInfo>() {
                    @Override
                    public void accept(ActivityResultInfo activityResultInfo) throws Exception {
                        Log.v("sun--", activityResultInfo.toString());
                        String path = activityResultInfo.getData().getStringExtra(PhotoConfig.RESULT_PHOTO_PATH);
                        Log.v("sun--", path);
                    }
                });
    }

    public void onTakeClick(View view){
        String savePath = getExternalCacheDir().getAbsolutePath() + "/q.jpg";
        UtilsCore.manager().takePicture(this, 100,savePath)
                .subscribe(new Consumer<ActivityResultInfo>() {
                    @Override
                    public void accept(ActivityResultInfo activityResultInfo) throws Exception {
                        Log.v("sun--", activityResultInfo.toString());
                        String path = activityResultInfo.getData().getStringExtra(PhotoConfig.RESULT_PHOTO_PATH);
                        Log.v("sun--", path);
                    }
                });
    }
}
