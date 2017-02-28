package com.example.administrator.apolloblechat.base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.apolloblechat.constant.AppConfig;
import com.example.administrator.apolloblechat.utils.CrashHandler;
import com.example.administrator.apolloblechat.utils.ResUtils;
import com.example.administrator.greendao.dao.DaoMaster;
import com.example.administrator.greendao.dao.DaoSession;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2016/8/24.
 */
public class BaseApplication extends Application {
    private static Context mContext;
    private static BaseApplication mBaseApplication;
    public DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        init();
    }

    private void init() {
        mBaseApplication = this;

        ResUtils.updateContext(mContext);
        //初始化数据库
        initDao();
        //初始化错误日志记录
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        //内存泄漏检测工具初始化
        LeakCanary.install(this);
    }

    private void initDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, AppConfig.DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        daoSession = master.newSession();
    }

    public static Context getContext() {
        return mContext;
    }

    public static BaseApplication getBaseApplication() {
        return mBaseApplication;
    }
}
