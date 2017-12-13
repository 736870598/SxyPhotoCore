package com.sunxiaoyu.photocore.core.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sunxiaoyu.photocore.PhotoConfig;
import com.sunxiaoyu.photocore.R;
import com.sunxiaoyu.photocore.core.view.ImageItemFragment;

import java.util.ArrayList;

/**
 *
 * Created by SunXiaoyu on 2017/12/11/011.
 */

public class SxySeePhotoActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ArrayList<String> pathList;
    private boolean isOne;
    private int index = 0;
    private int photoSize;

    private TextView indexText;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isOne = getIntent().getBooleanExtra(PhotoConfig.ONE_PHOTO, true);
        if (isOne){
            String path = getIntent().getStringExtra(PhotoConfig.PHOTO_PATH);
            pathList = new ArrayList<>(2);
            pathList.add(path);
        }else{
            pathList = getIntent().getStringArrayListExtra(PhotoConfig.PHOTO_PATH_LIST);
            index = getIntent().getIntExtra(PhotoConfig.SEE_POS, 0);
        }

        if (pathList == null || pathList.isEmpty()){
            finish();
            return;
        }

        photoSize = pathList.size();

        setContentView(R.layout.module_photo_see_layout);

        viewPager = (ViewPager) findViewById(R.id.see_photo_viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), pathList));
        if (!isOne){
            indexText = (TextView) findViewById(R.id.see_photo_textview);
            indexText.setText((index+1) + "/" + photoSize);
            indexText.setVisibility(View.VISIBLE);
            viewPager.addOnPageChangeListener(this);
            viewPager.setCurrentItem(index);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

    @Override
    public void onPageScrollStateChanged(int state) { }

    @Override
    public void onPageSelected(int position) {
        indexText.setText((position+1) + "/" + photoSize);
    }




    class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<String> pathList;

        public ViewPagerAdapter(FragmentManager fragmentManager, ArrayList<String> pathList){
            super(fragmentManager);
            this.pathList = pathList;
        }

        @Override
        public int getCount() {
            return pathList.size();
        }

        @Override
        public Fragment getItem(int position) {
            ImageItemFragment fragment = new ImageItemFragment();
            Bundle bundle = new Bundle();
            bundle.putString("photoPath", pathList.get(position));
            fragment.setArguments(bundle);
            return fragment;
        }

    }
}
