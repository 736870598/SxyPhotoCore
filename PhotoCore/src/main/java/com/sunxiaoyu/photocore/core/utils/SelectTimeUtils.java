package com.sunxiaoyu.photocore.core.utils;

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

    public static void showSelectDialog(final Context context, long time, final OnSelectListener listener ){

        final Calendar selectTime = Calendar.getInstance();
        selectTime.setTimeInMillis(time);

        DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                selectTime.set(Calendar.YEAR, year);
                selectTime.set(Calendar.MONTH, month);
                selectTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                shoeTimeSelectDialog(context, selectTime, listener);
            }
        }, selectTime.get(Calendar.YEAR),selectTime.get(Calendar.MONTH),selectTime.get(Calendar.DAY_OF_MONTH));

        dialog.show();
    }

    private static void shoeTimeSelectDialog(Context context, final Calendar selectTime, final OnSelectListener listener){
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                selectTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                selectTime.set(Calendar.MINUTE, minute);
                listener.onSelect(selectTime.getTimeInMillis());
            }
        }, selectTime.get(Calendar.HOUR_OF_DAY), selectTime.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    public interface OnSelectListener{
        void onSelect(long temp);
    }

}