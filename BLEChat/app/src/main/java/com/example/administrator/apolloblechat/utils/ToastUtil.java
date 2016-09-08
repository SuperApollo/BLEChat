package com.example.administrator.apolloblechat.utils;

import android.widget.Toast;

import com.example.administrator.apolloblechat.base.BaseApplication;

/**
 * Created by Administrator on 2016/9/2.
 */
public class ToastUtil {

    private static ToastUtil mToastUtil;
    private static Toast mToast;

    private ToastUtil() {
    }

    public static ToastUtil getInstance() {
        if (mToastUtil == null) {
            synchronized (ToastUtil.class) {
                if (mToastUtil == null) {
                    mToastUtil = new ToastUtil();
                }
            }
        }
        return mToastUtil;
    }

    public static void toaster(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getmContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.cancel();
            mToast = Toast.makeText(BaseApplication.getmContext(), msg, Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}
