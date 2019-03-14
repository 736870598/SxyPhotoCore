package com.sunxy.testmodule;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.sunxiaoyu.utils.UtilsCore;

import io.reactivex.functions.Consumer;


/**
 * SunXiaoYu on 2019/3/14.
 * mail: sunxiaoyu@hexinpass.com
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UtilsCore.manager().requestPermissions(this,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.v("sun--", "requestPermissions : " + aBoolean);

                    }
                });
    }

    public void onBackBtnClick(View view){
        Intent intent = new Intent();
        intent.putExtra("name", "11");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
