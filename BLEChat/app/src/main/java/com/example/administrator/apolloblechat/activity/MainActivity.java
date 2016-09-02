package com.example.administrator.apolloblechat.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.fragment.ChatFragment;
import com.example.administrator.apolloblechat.fragment.ContactFragment;
import com.example.administrator.apolloblechat.fragment.DeviceListFragment;
import com.example.administrator.apolloblechat.fragment.SettingFragment;
import com.example.administrator.apolloblechat.fragment.TransportFragment;
import com.example.administrator.apolloblechat.utils.FragmentUtils;
import com.example.administrator.apolloblechat.utils.ToastUtil;

/**
 * Created by Administrator on 2016/8/29.
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioButton rb_item_device_list;
    private RadioButton rb_item_chat;
    private RadioButton rb_item_transport;
    private RadioGroup rg_bottom;
    private long mExitTime;
    private static final int FIRST = 10086;
    private static final int SECOND = 10087;
    private static final int THIRD = 10088;
    private static final int FOUTH = 10089;
    private int currentFragment = FIRST;

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
                if (onCurrentFG(FIRST))
                    return;
                FragmentUtils.replace(this, R.id.ll_fragment_container, new DeviceListFragment());
                currentFragment = FIRST;
                break;
            case R.id.rb_item_contact:
                if (onCurrentFG(SECOND))
                    return;
                FragmentUtils.replace(this, R.id.ll_fragment_container, new ContactFragment());
                currentFragment = SECOND;
                break;
            case R.id.rb_item_transport:
                if (onCurrentFG(THIRD))
                    return;
                FragmentUtils.replace(this, R.id.ll_fragment_container, new TransportFragment());
                currentFragment = THIRD;
                break;
            case R.id.rb_item_setting:
                if (onCurrentFG(FOUTH))
                    return;
                FragmentUtils.replace(this, R.id.ll_fragment_container, new SettingFragment());
                currentFragment = FOUTH;
                break;
            default:
                break;
        }
    }

    /**
     * 判断当前是否position的fragment
     *
     * @param positon
     * @return
     */
    private boolean onCurrentFG(int positon) {
        boolean flag = false;
        if (positon == currentFragment) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtil.toaster("再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
