package com.example.administrator.apolloblechat.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Administrator on 2016/8/24.
 */
public class FragmentUtils {
    public static void replace(FragmentActivity activity, int containerId, Fragment to) {
        replace(activity, containerId, to, null);
    }

    public static void replace(FragmentActivity activity, int containerId, Fragment to, Bundle bundle) {
        FragmentManager manager = activity.getSupportFragmentManager();
        if (bundle != null) {
            to.setArguments(bundle);
        }
        manager.beginTransaction().replace(containerId, to).commit();
    }
}
