package com.example.administrator.apolloblechat.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.LinkedList;
import java.util.List;

/**
 * 名称：ActivityManager.java
 * <p/>
 * 描述：用于处理退出程序时可以退出所有的activity，而编写的通用类
 */
public class ActivityManager {

    private List<Activity> activityList = new LinkedList<Activity>();
    private static ActivityManager instance;

    private ActivityManager() {
    }

    /**
     * 单例模式中获取唯一的AbActivityManager实例.
     *
     * @return
     */
    public static ActivityManager getInstance() {
        if (null == instance) {
            synchronized (ActivityManager.class){
                instance = new ActivityManager();
            }
        }
        return instance;
    }

    /**
     * 添加Activity到容器中.
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 移除Activity从容器中.
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 遍历所有Activity并finish.
     */
    public void removeAllActivity() {
        for (Activity activity : activityList) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 退出程序的方法
     */
    public void exit(Context context) {

        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(startMain);
//			removeAllActivity();
        System.exit(0);
    }

    public void exitApp() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }
}