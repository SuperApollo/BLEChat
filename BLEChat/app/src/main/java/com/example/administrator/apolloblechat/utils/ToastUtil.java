package com.example.administrator.apolloblechat.utils;

import android.widget.Toast;

import com.example.administrator.apolloblechat.base.BaseApplication;

/**
 * Created by Administrator on 2016/9/2.
 */
public class ToastUtil {
    public static void toaster(String msg) {
        Toast.makeText(BaseApplication.getmContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
