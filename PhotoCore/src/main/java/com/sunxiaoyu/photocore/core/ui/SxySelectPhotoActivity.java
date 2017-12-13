package com.sunxiaoyu.photocore.core.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sunxiaoyu.photocore.PhotoConfig;
import com.sunxiaoyu.photocore.SxyUtilsManager;
import com.sunxiaoyu.photocore.core.utils.FileUtils;

/**
 *
 * Created by SunXiaoyu on 2017/12/11/011.
 */

public class SxySelectPhotoActivity extends Activity {

    private static final int SELECT_PHOTO_CODE = 101;

    private boolean needCompress;
    private String savePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        needCompress = getIntent().getBooleanExtra(PhotoConfig.NEED_COMPRESS, false);
        if (needCompress){
            savePath = getIntent().getStringExtra(PhotoConfig.SAVE_PATH);
            if (savePath == null){
                needCompress = false;
            }
        }

        Intent albumIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(albumIntent, SELECT_PHOTO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == Activity.RESULT_OK && requestCode == SELECT_PHOTO_CODE){
            String imagePath = FileUtils.getImagePathFromUri(this, data.getData());
            if (imagePath != null){
                if (needCompress){
                    needCompress = false;
                    SxyUtilsManager.getManager().compress(imagePath, savePath, 30);
                    imagePath = savePath;
                }
                data = new Intent();
                data.putExtra(PhotoConfig.RESULT_PHOTO_PATH, imagePath);
                setResult(Activity.RESULT_OK, data);
            }
            finish();
        }
    }
}
