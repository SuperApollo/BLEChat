package com.example.administrator.apolloblechat.base;

import android.app.Application;
import android.content.Context;

import com.example.administrator.apolloblechat.utils.ResUtils;

/**
 * Created by Administrator on 2016/8/24.
 */
public class BaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        init();
    }

    private void init() {
        ResUtils.updateContext(mContext);
    }

    public static Context getmContext() {
        return mContext;
    }
}
