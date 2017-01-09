package com.example.administrator.apolloblechat.activity;

import android.view.View;

import com.example.administrator.apolloblechat.R;

/**
 * Created by zayh_yf20160909 on 2017/1/9.
 */

public class FindDeviceActivity extends BaseActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_find_device;
    }

    @Override
    protected boolean fullScreen() {
        return false;
    }

    @Override
    protected boolean changeSystemBar() {
        return true;
    }

    @Override
    protected void initView(View view) {

    }
}
