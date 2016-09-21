package com.example.administrator.apolloblechat.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;

import com.example.administrator.apolloblechat.R;


/**
 * Created by blade on 2016/5/15.
 */
public class ShortcutInstallUtils {

    public static void addShortcutToDesktop(Context context,int resourceId) {

        try {
            Intent shortcut = new Intent(
                    "com.android.launcher.action.INSTALL_SHORTCUT");
            // 不允许重建
            shortcut.putExtra("duplicate", false);
            // 设置名字
            shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, ResUtils.getString(R.string.app_name));
            // 设置图标
            shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                    Intent.ShortcutIconResource.fromContext(context,

                            resourceId));
            // 设置意图和快捷方式关联程序
            Intent intent = new Intent(context, context.getClass());
            // 桌面图标和应用绑定，卸载应用后系统会同时自动删除图标
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);

            // 发送广播
            context.sendBroadcast(shortcut);
            ToastUtil.toaster("创建快捷方式到桌面");
        }catch (Exception e){

        }

    }

    /**
     * 快捷方式信息是保存在com.android.launcher的launcher.db的favorites表中
     *
     * @return
     */
    public static boolean isShortcutInstalled(Context context) {
        boolean isInstallShortcut = false;
        final ContentResolver cr = context
                .getContentResolver();
        // 2.2系统是”com.android.launcher2.settings”,网上见其他的为"com.android.launcher.settings"
        String AUTHORITY = null;
        /*
         * 根据版本号设置Uri的AUTHORITY
         */
        if(getSystemVersion() >= 8){
            AUTHORITY = "com.android.launcher2.settings";
        }else{
            AUTHORITY = "com.android.launcher.settings";
        }

        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/favorites?notify=true");
        Cursor c = cr.query(CONTENT_URI,
                new String[] { "title", "iconResource" }, "title=?",
                new String[] { ResUtils.getString(R.string.app_name) }, null);// XXX表示应用名称。
        if (c != null && c.getCount() > 0) {
            isInstallShortcut = true;
        }
        return isInstallShortcut;
    }

    /**
     * 获取系统的SDK版本号
     * @return
     */
    private static int getSystemVersion(){
        return Build.VERSION.SDK_INT;
    }


}


