package com.example.administrator.apolloblechat.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * 弹框工具类
 * Created by Administrator on 2016/11/8.
 */

public class MyPopUtil {
    private static MyPopUtil mMyPopUtil = null;
    private Context mContext;//必须是activity
    private int mLayoutId;//填充布局id
    private int mWidth;//显示位置
    private int mHeight;//显示位置
    private int mAnim;//显示的动画效果
    private View mPopView;//填充的布局
    private PopupWindow mMyPopupWindow;

    public PopupWindow getmMyPopupWindow() {
        return mMyPopupWindow;
    }

    public View getmPopView() {
        return mPopView;
    }

    public void setmPopView(View mPopView) {
        this.mPopView = mPopView;
    }


    public MyPopUtil(Context context) {
        this.mContext = context;
    }


    public static MyPopUtil getInstance(Context context) {
        if (mMyPopUtil == null) {
            synchronized (MyPopUtil.class) {
                if (mMyPopUtil == null) {
                    mMyPopUtil = new MyPopUtil(context);
                }
            }
        }
        return mMyPopUtil;
    }

    public View initView(int layoutId, int width, int height, int anim) {
        View popView = LayoutInflater.from(mContext).inflate(layoutId, null);
        if (mMyPopupWindow == null)
            mMyPopupWindow = new PopupWindow();
        mMyPopupWindow.setContentView(popView);
        mMyPopupWindow.setWidth(width);
        mMyPopupWindow.setHeight(height);
        mMyPopupWindow.setOutsideTouchable(true);
        mMyPopupWindow.setFocusable(true);
        //为了使点击popupwindow以外的地方使其能消失
        mMyPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mMyPopupWindow.setAnimationStyle(anim);
        mMyPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        mPopView = popView;
        return popView;
    }

    /**
     * 指定位置显示
     *
     * @param parent  父布局
     * @param gravity 布局
     * @param x       相对
     * @param y       相对
     */
    public void showAtLoacation(View parent, int gravity, int x, int y) {
        if (mMyPopupWindow != null) {
            backgroundAlpha(0.5f);
            mMyPopupWindow.showAtLocation(parent, gravity, x, y);
        }
    }

    /**
     * 显示在指定view下面
     *
     * @param anchor
     * @param x
     * @param y
     */
    public void showAsDropDown(View anchor, int x, int y) {
        if (mMyPopupWindow != null) {
            backgroundAlpha(0.5f);
            mMyPopupWindow.showAsDropDown(anchor, x, y);
        }
    }

    public void dismiss() {
        if (mMyPopupWindow != null) {
            mMyPopupWindow.dismiss();
        }

    }

    public void goneItem() {

    }

    /**
     * 设置popupwindow以外背景透明度
     *
     * @param bgAlpha
     */
    private void backgroundAlpha(float bgAlpha) {
        Activity activity = (Activity) mContext;
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug
        }
        activity.getWindow().setAttributes(lp);
    }

}
