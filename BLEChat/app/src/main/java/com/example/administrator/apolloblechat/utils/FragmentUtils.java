package com.example.administrator.apolloblechat.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

/**
 * Created by Administrator on 2016/8/24.
 */
public class FragmentUtils {
    public static void replace(Activity activity, int container, Fragment fragment) {
        FragmentManager fm = activity.getFragmentManager();
        fm.beginTransaction().replace(container, fragment).commit();
    }
}
