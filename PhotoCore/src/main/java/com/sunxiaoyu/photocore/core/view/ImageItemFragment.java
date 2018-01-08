package com.sunxiaoyu.photocore.core.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sunxiaoyu.photocore.core.GlideUtils;
import com.sunxiaoyu.photocore.core.photoview.PhotoView;
import com.sunxiaoyu.photocore.core.photoview.PhotoViewAttacher;

/**
 * @author: SunXiaoYu
 * @date: 2017/12/13
 */

public class ImageItemFragment extends Fragment implements  PhotoViewAttacher.ISingleTop{

    private PhotoView photoView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (photoView == null){
            photoView = new PhotoView(getActivity());
            photoView.setSingleTop(this);
            photoView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        String photoPath = getArguments().getString("photoPath");
        GlideUtils.loadImage(getContext(), photoPath, photoView);


        return photoView;
    }

    @Override
    public void onSingleClick() {
        getActivity().onBackPressed();
    }
}
