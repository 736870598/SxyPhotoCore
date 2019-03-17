package com.sunxiaoyu.utils.core.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sunxiaoyu.utils.core.photoview.PhotoView;
import com.sunxiaoyu.utils.core.photoview.PhotoViewAttacher;
import com.sunxiaoyu.utils.core.utils.ImageUtils;
import com.sunxiaoyu.utils.core.utils.StringUtils;

/**
 * @author: SunXiaoYu
 * @date: 2017/12/13
 */

public class ImageItemFragment extends Fragment implements PhotoViewAttacher.ISingleTop{

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
        if (StringUtils.isNotNull(photoPath)){
            ImageUtils.loadImage(this, photoView, photoPath);
        }

        return photoView;
    }

    @Override
    public void onSingleClick() {
        getActivity().onBackPressed();
    }
}
