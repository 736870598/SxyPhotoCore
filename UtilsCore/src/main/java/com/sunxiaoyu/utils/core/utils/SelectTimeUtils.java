package com.sunxiaoyu.utils.core.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * 时间选择器
 * Created by Administrator on 2017/3/18/018.
 */
public class SelectTimeUtils {

    /**
     * 选择日期和时间
     * @param context       上下文
     * @param time          显示的时间
     * @param listener      选择监听器
     */
    public static void selectDataAndTimeDialog(final Context context, long time, final OnSelectListener listener ){

        final Calendar selectTime = Calendar.getInstance();
        selectTime.setTimeInMillis(time);

        DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                selectTime.set(Calendar.YEAR, year);
                selectTime.set(Calendar.MONTH, month);
                selectTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                selectTimeDialog(context, selectTime, listener);
            }
        }, selectTime.get(Calendar.YEAR),selectTime.get(Calendar.MONTH),selectTime.get(Calendar.DAY_OF_MONTH));

        dialog.show();
    }

    /**
     * 选择日期
     * @param context       上下文
     * @param time          显示的时间
     * @param listener      选择监听器
     */
    public static void selectDataDialog(final Context context, long time, final OnSelectListener listener ){

        final Calendar selectTime = Calendar.getInstance();
        selectTime.setTimeInMillis(time);

        DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                selectTime.set(Calendar.YEAR, year);
                selectTime.set(Calendar.MONTH, month);
                selectTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if (listener != null){
                    listener.onSelect(selectTime.getTimeInMillis());
                }
            }
        }, selectTime.get(Calendar.YEAR),selectTime.get(Calendar.MONTH),selectTime.get(Calendar.DAY_OF_MONTH));

        dialog.show();
    }

    /**
     * 选择时间
     * @param context       上下文
     * @param time          显示的时间
     * @param listener      选择监听器
     */
    public static void selectTimeDialog(Context context, final Calendar time, final OnSelectListener listener){
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time.set(Calendar.HOUR_OF_DAY, hourOfDay);
                time.set(Calendar.MINUTE, minute);
                if (listener != null){
                    listener.onSelect(time.getTimeInMillis());
                }
            }
        }, time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    public interface OnSelectListener{
        void onSelect(long temp);
    }

}