package com.example.administrator.apolloblechat.fragment;

import android.view.View;

import com.example.administrator.apolloblechat.R;

/**
 * Created by Administrator on 2016/9/20.
 */
public class WelcomeFragmentFirstTime extends BaseFragment {
    @Override
    protected int getViewId() {
        return R.layout.fragment_welcome_first_time;
    }

    @Override
    protected void initView(View view) {
        mToastUtil.toaster("首次运行viewpager界面");
    }
}
