package com.example.administrator.apolloblechat.activity;

import android.os.Bundle;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.fragment.DeviceListFragment;
import com.example.administrator.apolloblechat.utils.FragmentUtils;

/**
 * Created by Administrator on 2016/8/29.
 */
public class MainActivity extends BaseActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initView();
    }

    private void initView() {

    }

    private void init() {
        FragmentUtils.replace(this, R.id.ll_fragment_container, new DeviceListFragment());
    }
}
