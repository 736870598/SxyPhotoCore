package com.sunxiaoyu.sxyphoto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String sdsd = getIntent().getStringExtra("n");
        TextView textView = new TextView(this);
        textView.setText(sdsd);
        setContentView(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("n", "secondsdsdsd");
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}
