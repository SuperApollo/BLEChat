package com.example.administrator.apolloblechat.base;

import android.app.Application;
import android.content.Context;

import com.example.administrator.apolloblechat.utils.CrashHandler;
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
        //初始化数据库
        initDao();
        //初始化错误日志记录
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

    }

    private void initDao() {

    }

    public static Context getContext() {
        return mContext;
    }
}
