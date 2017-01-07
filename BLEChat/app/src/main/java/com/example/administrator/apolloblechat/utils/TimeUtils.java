package com.example.administrator.apolloblechat.utils;

import com.example.administrator.apolloblechat.constant.ChatTime;

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

    /**
     * 信息页列表时间显示
     *
     * @param time
     * @return
     */
    public static String changeTime(String time) {
        String showTime = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long millis = 0;
        Date date = null;
        try {
            date = sdf.parse(time);
            millis = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        Calendar today = Calendar.getInstance();
        Date d = new Date(System.currentTimeMillis());
        today.setTime(d);

        Calendar showDay = Calendar.getInstance();
        Date d1 = new Date(millis);
        showDay.setTime(d1);
        int i = today.get(Calendar.DAY_OF_YEAR) - showDay.get(Calendar.DAY_OF_YEAR);

        SimpleDateFormat s = new SimpleDateFormat("HH:mm");
        String onlyTime = s.format(date);

        if (i == 0) {
            //今天
            showTime = onlyTime;
        } else if (i == 1) {
            //昨天
            showTime = "昨天";
        } else if ((i < 7) && (i > 1)) {
            //一星期以内
            String week = String.valueOf(showDay.get(Calendar.DAY_OF_WEEK));
            switch (week) {
                case "1":
                    showTime = "星期日";
                    break;
                case "2":
                    showTime = "星期一";
                    break;
                case "3":
                    showTime = "星期二";
                    break;
                case "4":
                    showTime = "星期三";
                    break;
                case "5":
                    showTime = "星期四";
                    break;
                case "6":
                    showTime = "星期五";
                    break;
                case "7":
                    showTime = "星期六";
                    break;
            }

        } else {
            SimpleDateFormat s1 = new SimpleDateFormat("MM月dd日");
            showTime = s1.format(date);
        }

        return showTime;
    }

    /**
     * 聊天界面时间显示
     *
     * @param time
     * @return
     */
    public static String chatTime(String time) {
        String showTime = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long millis = 0;
        Date date = null;
        try {
            date = sdf.parse(time);
            millis = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        //内存存储的最后一次显示时间
        long lastShowMillis = ChatTime.lastShowMillis;
        if (lastShowMillis == -1) {
            ChatTime.lastShowMillis = millis;
        }

        long delta = Math.abs(millis - ChatTime.lastShowMillis);
        if (delta != 0) {//不是第一次显示
            if (delta < 120000) {
                //距离最后一次显示间隔小于2分钟，不予显示
                return null;
            } else {
                //距离最后一次显示间隔大于2分钟，显示，并且替换掉最后一次显示时间
                ChatTime.lastShowMillis = millis;
            }

        }

        Calendar today = Calendar.getInstance();
        Date d = new Date(System.currentTimeMillis());
        today.setTime(d);

        Calendar showDay = Calendar.getInstance();
        Date d1 = new Date(millis);
        showDay.setTime(d1);
        int i = today.get(Calendar.DAY_OF_YEAR) - showDay.get(Calendar.DAY_OF_YEAR);

        SimpleDateFormat s = new SimpleDateFormat("HH:mm");
        String onlyTime = s.format(date);

        if (i == 0) {
            //今天
            showTime = onlyTime;
        } else if (i == 1) {
            //昨天
            showTime = "昨天 " + onlyTime;
        } else if ((i < 7) && (i > 1)) {
            //一星期以内
            String week = String.valueOf(showDay.get(Calendar.DAY_OF_WEEK));
            switch (week) {
                case "1":
                    showTime = "星期日 " + onlyTime;
                    break;
                case "2":
                    showTime = "星期一 " + onlyTime;
                    break;
                case "3":
                    showTime = "星期二 " + onlyTime;
                    break;
                case "4":
                    showTime = "星期三 " + onlyTime;
                    break;
                case "5":
                    showTime = "星期四 " + onlyTime;
                    break;
                case "6":
                    showTime = "星期五 " + onlyTime;
                    break;
                case "7":
                    showTime = "星期六 " + onlyTime;
                    break;
            }

        } else {
            SimpleDateFormat s1 = new SimpleDateFormat("MM月dd日");
            showTime = s1.format(date) + " " + onlyTime;
        }
        return showTime;
    }

    /**
     * 群公告列表显示的时间
     *
     * @param millis
     * @return
     */
    public static String groupNoticeTime(long millis) {
        String showTime = "";
        Date date = new Date(millis);

        Calendar today = Calendar.getInstance();
        Date d = new Date(System.currentTimeMillis());
        today.setTime(d);

        Calendar showDay = Calendar.getInstance();
        Date d1 = new Date(millis);
        showDay.setTime(d1);
        int i = today.get(Calendar.DAY_OF_YEAR) - showDay.get(Calendar.DAY_OF_YEAR);

        SimpleDateFormat s = new SimpleDateFormat("HH:mm");
        String onlyTime = s.format(date);

        if (i == 0) {
            //今天
            showTime = "今天 " + onlyTime;
        } else if (i == 1) {
            //昨天
            showTime = "昨天 " + onlyTime;
        } else if ((i < 7) && (i > 1)) {
            //一星期以内
            String week = String.valueOf(showDay.get(Calendar.DAY_OF_WEEK));
            switch (week) {
                case "1":
                    showTime = "星期日 " + onlyTime;
                    break;
                case "2":
                    showTime = "星期一 " + onlyTime;
                    break;
                case "3":
                    showTime = "星期二 " + onlyTime;
                    break;
                case "4":
                    showTime = "星期三 " + onlyTime;
                    break;
                case "5":
                    showTime = "星期四 " + onlyTime;
                    break;
                case "6":
                    showTime = "星期五 " + onlyTime;
                    break;
                case "7":
                    showTime = "星期六 " + onlyTime;
                    break;
            }

        } else {
            SimpleDateFormat s1 = new SimpleDateFormat("MM月dd日");
            showTime = s1.format(date) + " " + onlyTime;
        }
        return showTime;
    }
}
