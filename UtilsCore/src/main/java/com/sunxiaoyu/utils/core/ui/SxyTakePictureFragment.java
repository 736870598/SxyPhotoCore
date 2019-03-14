package com.sunxiaoyu.utils.core.ui;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

import com.sunxiaoyu.utils.core.PhotoConfig;
import com.sunxiaoyu.utils.core.model.ActivityResultInfo;
import com.sunxiaoyu.utils.core.utils.FileUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class SxyTakePictureFragment extends SxyBaseFragment {

    private static final int TAKE_PHOTO_CODE = 100;
    private String savePath;
    private Disposable disposable;

    @Override
    protected void doSomethingStart(Intent data) {
        savePath = data.getStringExtra(PhotoConfig.SAVE_PATH);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileUtils.path2Uri(getActivity(), savePath));
        startActivityForResult(intent, TAKE_PHOTO_CODE);
    }

    @Override
    protected void doSomethingResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == TAKE_PHOTO_CODE){
            disposable = Observable.just(savePath)
                    .subscribeOn(Schedulers.io())
                    .map(new Function<String, Intent>() {
                        @Override
                        public Intent apply(String savePath) throws Exception {
                            Intent data = new Intent();
                            data.putExtra(PhotoConfig.RESULT_PHOTO_PATH, savePath);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
