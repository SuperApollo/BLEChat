package com.example.administrator.apolloblechat.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import java.io.Serializable;
import java.util.ArrayList;

public class IntentUtils {
    /**
     * 页面跳转
     *
     * @param context
     * @param cls
     */
    @SuppressWarnings("rawtypes")
    public static void sendIntent(Context context, Class cls) {
        sendIntent(context, cls, null);
    }

    /**
     * 页面跳转
     *
     * @param context
     * @param cls
     */
    @SuppressWarnings("rawtypes")
    public static void sendIntent(Context context, Class cls, Bundle extras) {
        Intent intent = new Intent(context, cls);
        if (extras != null) {
            intent.putExtras(extras);
        }
        String res = Intent.ACTION_SHUTDOWN;
        context.startActivity(intent);
    }

    @SuppressWarnings("rawtypes")
    public static void sendIntentWithClearTop(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        ((Activity) context).finish();
    }


    /**
     * 设置wifi网络
     *
     * @param mActivity
     */
    public static void settingWifiNetwork(Activity mActivity) {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        mActivity.startActivity(intent);
    }


    /**
     * 拨打电话
     *
     * @param context
     * @param phone
     */
    public static void dialPhone(Context context, String phone) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);//取得相关系统服务
        int state = tm.getSimState();
        if (state == TelephonyManager.SIM_STATE_UNKNOWN) {
            ToastUtil.toaster("SIM卡异常");
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            context.startActivity(intent);
        }
    }

    /**
     * 发送短信
     *
     * @param sendContent
     * @param phones
     */
    public static void sendSMS(Context context, String phones, String sendContent) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra("address", phones);
        intent.putExtra("sms_body", sendContent);
        intent.setType("vnd.android-dir/mms-sms");
        context.startActivity(intent);
    }

    /**
     * 打开浏览器
     *
     * @param context
     * @param url
     */
    public static void openBrowser(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    /**
     * 设置gprs网络
     *
     * @param mActivity
     */
    public static void settingGprsNetwork(Activity mActivity) {
        Intent intent = null;
        if (Build.VERSION.SDK_INT > 10) {
            intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent(Intent.ACTION_VIEW);
            ComponentName component = new ComponentName("com.android.settings",
                    "com.android.settings.WirelessSettings");
            intent.setComponent(component);
        }
        mActivity.startActivity(intent);
    }


    /**
     * 获取intent string参数
     *
     * @param mActivity
     * @param savedInstanceState
     * @param paramName
     * @return
     */
    public static String getIntentStringParams(Activity mActivity,
                                               Bundle savedInstanceState, String paramName) {
        return savedInstanceState != null ? savedInstanceState
                .getString(paramName) : mActivity.getIntent().getStringExtra(
                paramName);
    }


    /**
     * 获取intent boolean参数
     *
     * @param mActivity
     * @param savedInstanceState
     * @param paramName
     * @return
     */
    public static boolean getIntentBooleanParams(Activity mActivity,
                                                 Bundle savedInstanceState, String paramName, boolean defaultValue) {
        return savedInstanceState != null ? savedInstanceState
                .getBoolean(paramName) : mActivity.getIntent().getBooleanExtra(
                paramName, defaultValue);
    }

    /**
     * 获取intent boolean参数
     *
     * @param mActivity
     * @param savedInstanceState
     * @param paramName
     * @return
     */
    public static int getIntentIntParams(Activity mActivity,
                                         Bundle savedInstanceState, String paramName, int defaultValue) {
        return savedInstanceState != null ? savedInstanceState
                .getInt(paramName) : mActivity.getIntent().getIntExtra(
                paramName, defaultValue);
    }

    /**
     * 获取intent参数
     *
     * @param mActivity
     * @param savedInstanceState
     * @param paramName
     * @return
     */
    public static ArrayList<String> getIntentStringArrayList(
            Activity mActivity, Bundle savedInstanceState, String paramName) {
        return savedInstanceState != null ? savedInstanceState
                .getStringArrayList(paramName) : mActivity.getIntent()
                .getStringArrayListExtra(paramName);
    }

    /**
     * 获取intent参数
     *
     * @param mActivity
     * @param savedInstanceState
     * @param paramName
     * @return
     */
    public static String[] getIntentStringArray(Activity mActivity,
                                                Bundle savedInstanceState, String paramName) {
        return savedInstanceState != null ? savedInstanceState
                .getStringArray(paramName) : mActivity.getIntent()
                .getStringArrayExtra(paramName);
    }

    /**
     * 获取intent Serializable参数
     *
     * @param mActivity
     * @param savedInstanceState
     * @param paramName
     * @return
     */
    public static Serializable getIntentSerializableParams(Activity mActivity,
                                                           Bundle savedInstanceState, String paramName) {
        return savedInstanceState != null ? savedInstanceState
                .getSerializable(paramName) : mActivity.getIntent()
                .getSerializableExtra(paramName);
    }
}
