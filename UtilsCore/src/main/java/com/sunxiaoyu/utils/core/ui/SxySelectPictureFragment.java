package com.sunxiaoyu.utils.core.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;

import com.sunxiaoyu.utils.UtilsCore;
import com.sunxiaoyu.utils.core.PhotoConfig;
import com.sunxiaoyu.utils.core.model.ActivityResultInfo;
import com.sunxiaoyu.utils.core.utils.FileUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * Created by Administrator on 2018/1/8 0008.
 */
public class SxySelectPictureFragment extends SxyBaseFragment {

    private static final int SELECT_PICTURE_CODE = 101;
    private boolean needCompress;
    private String savePath;

    @Override
    protected void doSomethingStart(Intent data) {
        needCompress = data.getBooleanExtra(PhotoConfig.NEED_COMPRESS, false);
        if (needCompress){
            savePath = data.getStringExtra(PhotoConfig.SAVE_PATH);
            if (savePath == null){
                needCompress = false;
            }
        }
        Intent albumIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(albumIntent, SELECT_PICTURE_CODE);
    }

    @Override
    protected void doSomethingResult(int requestCode, int resultCode, Intent data) {
        if (data != null && resultCode == Activity.RESULT_OK && requestCode == SELECT_PICTURE_CODE){
            Observable.just(data)
                    .subscribeOn(Schedulers.io())
                    .map(new Function<Intent, Intent>() {
                        @Override
                        public Intent apply(Intent data) throws Exception {
                            String imagePath = FileUtils.getImagePathFromUri(getActivity(), data.getData());
                            if (imagePath != null){
                                if (needCompress){
                                    needCompress = false;
                                    //压缩图片
                                    UtilsCore.manager().compress(BitmapFactory.decodeFile(imagePath), savePath, 30);
                                    imagePath = savePath;
                                }
                            }
                            data = new Intent();
                            data.putExtra(PhotoConfig.RESULT_PHOTO_PATH, imagePath);
                            return data;
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Intent>() {
                        @Override
                        public void accept(Intent data) throws Exception {
                            setResult(new ActivityResultInfo(data), true);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            setResult(new ActivityResultInfo(throwable.getMessage()), true);
                            throwable.printStackTrace();
                        }
                    });
            setResult(new ActivityResultInfo(1, null), false);
        }else{
            setResult(new ActivityResultInfo(2, data), true);
        }
    }
}
