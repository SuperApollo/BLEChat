package com.example.administrator.apolloblechat.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.utils.SystemBarTintManager;
import com.example.administrator.apolloblechat.utils.ToastUtil;
import com.example.administrator.apolloblechat.widgets.CustomProgressView;

import java.util.zip.Inflater;

/**
 * Created by Administrator on 2016/8/23.
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    protected ToastUtil mToastUtil;
    private CustomProgressView customProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = getLayoutInflater().inflate(getContentViewId(), null);
        setContentView(view);
        initSystemBar(this);
        mToastUtil = ToastUtil.getInstance();
        initView(view);
    }

    protected abstract int getContentViewId();

    /****
     * 初始化系统标题栏  统一样式
     */
    private void initSystemBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.commom_title_bg);
        }
    }

    private static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    protected abstract void initView(View view);

    protected <T extends View> T queryViewById(View parentView, int id) {
        return (T) parentView.findViewById(id);
    }

    protected <T extends View> T queryViewById(View parentView, int id, boolean onClickListener) {
        T view = (T) parentView.findViewById(id);
        if (onClickListener) {
            view.setOnClickListener(this);
        }
        return view;
    }


    @Override
    public void onClick(View view) {

    }

    //显示进度条
    public void showProgress() {
        if (customProgressView == null) {
            customProgressView = new CustomProgressView(this)
                    .setCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            customProgressViewCancel();
                        }
                    });
        }
        customProgressView.showProgressDialog();
    }

    //清理進度條
    public void clearProgress() {
        if (customProgressView != null) {
            customProgressView.dissDialog();
            customProgressView = null;
        }
    }

    public void customProgressViewCancel() {

    }
}
