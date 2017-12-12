package com.sunxiaoyu.photocore.core;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;

import com.sunxiaoyu.photocore.PhotoConfig;

/**
 * 拍照
 *
 * Created by SunXiaoyu on 2017/12/11/011.
 */
public class SxyTakePhotoActivity extends Activity implements RxPermissionsManager.PermissionDealListener{

    private static final int TAKE_PHOTO_CODE = 100;

    private boolean needCompress;
    private String savePath;
    private boolean isGoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        needCompress = getIntent().getBooleanExtra(PhotoConfig.NEED_COMPRESS, false);
        savePath = getIntent().getStringExtra(PhotoConfig.SAVE_PATH);

        if (savePath == null){
            finish();
            return;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        RxPermissionsManager.getManager().requestPermissions(this, this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PHOTO_CODE && resultCode == Activity.RESULT_OK){
            if (needCompress){ //

            }
            data = new Intent();
            data.putExtra(PhotoConfig.RESULT_PHOTO_PATH, savePath);
            setResult(Activity.RESULT_OK, data);
        }
        finish();
    }

    @Override
    public void havePermission() {
        if (!isGoto){
            isGoto = true;
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, FileUtils.path2Uri(this, savePath));
            startActivityForResult(intent, TAKE_PHOTO_CODE);
        }
    }
}
