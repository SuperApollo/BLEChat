package com.example.administrator.apolloblechat.activity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.fragment.ChatFragment;
import com.example.administrator.apolloblechat.fragment.DeviceListFragment;
import com.example.administrator.apolloblechat.fragment.TransportFragment;
import com.example.administrator.apolloblechat.utils.FragmentUtils;

/**
 * Created by Administrator on 2016/8/29.
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioButton rb_item_device_list;
    private RadioButton rb_item_chat;
    private RadioButton rb_item_transport;
    private RadioGroup rg_bottom;

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
        rb_item_device_list = (RadioButton) findViewById(R.id.rb_item_device_list);
        rb_item_chat = (RadioButton) findViewById(R.id.rb_item_chat);
        rb_item_transport = (RadioButton) findViewById(R.id.rb_item_transport);
        rg_bottom = (RadioGroup) findViewById(R.id.rg_bottom);

        rg_bottom.setOnCheckedChangeListener(this);


    }

    private void init() {
        FragmentUtils.replace(this, R.id.ll_fragment_container, new DeviceListFragment());
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.rb_item_device_list:
                FragmentUtils.replace(this,R.id.ll_fragment_container,new DeviceListFragment());
                break;
            case R.id.rb_item_chat:
                FragmentUtils.replace(this,R.id.ll_fragment_container,new ChatFragment());
                break;
            case R.id.rb_item_transport:
                FragmentUtils.replace(this,R.id.ll_fragment_container,new TransportFragment());
                break;
        }
    }
}
