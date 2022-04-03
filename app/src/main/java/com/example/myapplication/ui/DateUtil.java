package com.example.myapplication.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String formatDate(long time) {
        //获取月日
        SimpleDateFormat format = new SimpleDateFormat("MM/dd HH:mm");
        Date date = new Date(time);
        return format.format(date);
    }

}
