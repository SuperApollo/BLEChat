package com.example.administrator.apolloblechat.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/14.
 */
public class TimeUtils {
    private static final String TIME_FOMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FOMAT);
        String t = sdf.format(date);
        return t;
    }

    /**
     * 时间转毫秒
     *
     * @param time
     * @return
     */
    public static long timeToMill(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FOMAT);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long millis = c.getTimeInMillis();
        return millis;
    }

    /**
     * 毫秒转时间
     *
     * @param mill
     * @return
     */
    public static String millToTime(long mill) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FOMAT);
        Date date = new Date(mill);
        return sdf.format(date);
    }
}
