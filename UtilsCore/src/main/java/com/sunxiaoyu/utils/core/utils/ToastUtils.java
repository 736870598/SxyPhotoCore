package com.sunxiaoyu.utils.core.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.sunxiaoyu.utils.R;

/**
 * ToastUtils
 */
public class ToastUtils {

    private static Toast toast;
    private static TextView view;

    public static void showToast(Context context, String text) {
        if (StringUtils.isNull(text)) {
            return;
        }
        if (toast == null) {
            toast = new Toast(context.getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);
            view = new TextView(context);
            view.setTextColor(Color.WHITE);
            view.setBackgroundResource(R.drawable.black_circular_view);
            view.setGravity(Gravity.CENTER);
            int size = (int) view.getTextSize();
            view.setPadding(size,size,size,size);
            toast.setView(view);
        }
        view.setText(text);
        if (toast != null) {
            toast.show();
        }
    }
}
