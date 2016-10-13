package com.example.administrator.apolloblechat.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.utils.ActivityManager;
import com.example.administrator.apolloblechat.fragment.ContactFragment;
import com.example.administrator.apolloblechat.fragment.DeviceListFragment;
import com.example.administrator.apolloblechat.fragment.MoreFragment;
import com.example.administrator.apolloblechat.fragment.TransportFragment;
import com.example.administrator.apolloblechat.utils.FragmentUtils;
import com.example.administrator.apolloblechat.utils.ToastUtil;

/**
 * Created by Administrator on 2016/8/29.
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg_bottom;
    private long mExitTime;
    private static final int FIRST = 10086;
    private static final int SECOND = 10087;
    private static final int THIRD = 10088;
    private static final int FOUTH = 10089;
    private int currentFragment = FIRST;
    private OnBackKeyPressedListner mOnBackKeyPressedListner;

    public void setmOnBackKeyPressedListner(OnBackKeyPressedListner mOnBackKeyPressedListner) {
        this.mOnBackKeyPressedListner = mOnBackKeyPressedListner;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
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
        rg_bottom = queryViewById(view, R.id.rg_bottom);
        rg_bottom.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
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
            case R.id.rb_item_more:
                if (onCurrentFG(FOUTH))
                    return;
                FragmentUtils.replace(this, R.id.ll_fragment_container, new MoreFragment());
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
    /*    if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtil.toaster("再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                ActivityManager.getInstance().exitApp();
            }
            return true;
        }*/

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (null != mOnBackKeyPressedListner) {
            boolean hasFragment = mOnBackKeyPressedListner.onPressed();
            mOnBackKeyPressedListner = null;
            if (!hasFragment) {//为解决按两次返回键才退出程序问题
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }

    }


    /**
     * 将返回按键事件传递给fragment的接口
     * 布尔值表示有无返回fragment
     */
    public interface OnBackKeyPressedListner {
        boolean onPressed();
    }
}
