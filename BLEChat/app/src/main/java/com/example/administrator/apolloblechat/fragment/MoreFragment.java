package com.example.administrator.apolloblechat.fragment;

import android.app.Dialog;
import android.os.Build;
import android.view.View;

import com.example.administrator.apolloblechat.R;
import com.example.administrator.apolloblechat.activity.LoginActivity;
import com.example.administrator.apolloblechat.activity.WelcomeActivity;
import com.example.administrator.apolloblechat.base.ActivityManager;
import com.example.administrator.apolloblechat.base.BaseApplication;
import com.example.administrator.apolloblechat.constant.AppConfig;
import com.example.administrator.apolloblechat.flash.Flash;
import com.example.administrator.apolloblechat.flash.FlashLightManager;
import com.example.administrator.apolloblechat.utils.FragmentUtils;
import com.example.administrator.apolloblechat.utils.IntentUtils;
import com.example.administrator.apolloblechat.utils.SharedPreferencesUtils;
import com.example.administrator.apolloblechat.widgets.ItemView;
import com.example.administrator.apolloblechat.widgets.MyDialog;
import com.zcw.togglebutton.ToggleButton;

/**
 * Created by Administrator on 2016/9/2.
 */
public class MoreFragment extends BaseFragment {

    private ToggleButton toggleButton;
    private boolean mUpper;
    private FlashLightManager mFlashLightManager;
    private Flash mFlash;

    @Override
    protected int getViewId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initView(View view) {

        toggleButton = queryViewById(view, R.id.toggle_item_more_flash);
        toggleButton.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean b) {
                if (mUpper) {
                    mFlashLightManager.setFlashlight(b);
                } else {
                    mFlash.setLight(b);
                }
            }
        });

        setItemClick(view, R.id.item_more_mine, R.mipmap.icon_item_more_mine, "我的", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentUtils.replace(getActivity(),R.id.ll_fragment_container,new MineFragment());
            }
        }, true, false);
        setItemClick(view, R.id.item_more_setting, R.mipmap.icon_item_more_setting, "设置", new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new MyDialog.Builder(getActivity()).setCancelable(true)
                        .setMessage("进入设置")
                        .setButton1("取消", new MyDialog.OnClickListener() {
                            @Override
                            public void onClick(Dialog dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setButton2("确定", new MyDialog.OnClickListener() {
                            @Override
                            public void onClick(Dialog dialog, int which) {
                                mToastUtil.toaster("设置");
                                dialog.dismiss();
                            }
                        })
                        .setCanceledOnTouchOutside(true)
                        .create().show();
            }
        }, true, false);

        setItemClick(view, R.id.item_more_logout, R.mipmap.icon_item_more_exit, "退出登录", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyDialog.Builder(getActivity()).setCancelable(true)
                        .setMessage("退出登录")
                        .setButton1("取消", new MyDialog.OnClickListener() {
                            @Override
                            public void onClick(Dialog dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setButton2("确定", new MyDialog.OnClickListener() {
                            @Override
                            public void onClick(Dialog dialog, int which) {
                                SharedPreferencesUtils.putBoolean(AppConfig.IS_LOGIN, false);
                                IntentUtils.sendIntent(getActivity(), LoginActivity.class);
                                getActivity().finish();
                            }
                        })
                        .create().show();
            }
        }, false, false);

        setItemClick(view, R.id.item_more_about, R.mipmap.icon_item_more_about, "关于", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mToastUtil.toaster("关于");
            }
        }, false, false);

        initFlash();
    }

    private void initFlash() {

        mUpper = isVersion21Upper();
        if (mUpper) {
            mFlashLightManager = new FlashLightManager(mContext);
        } else {
            mFlash = Flash.getInstance();
            mFlash.open();
        }

    }

    //判断版本是否是5.0以上
    private boolean isVersion21Upper() {
        int version = Build.VERSION.SDK_INT;
        if (version >= 21) {
            return true;
        }
        return false;
    }

    private void setItemClick(View view, int viewID, int iconID, String name, View.OnClickListener listener, boolean lineVisibility, boolean badgeViewVisibility) {
        ItemView itemView = (ItemView) view.findViewById(viewID);
        itemView.setTextSize(16);
        itemView.setText(name);
        View line = itemView.findViewById(R.id.line_fragment_more);
        if (lineVisibility) {
            line.setVisibility(View.VISIBLE);
        }
        itemView.setTextDrawable(iconID);
        itemView.setOnClickListener(listener);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mUpper) {
            if (null == mFlashLightManager) {
                initFlash();
            }
        } else {
            if (null == mFlash) {
                initFlash();
            }
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFlashLightManager != null) {
            mFlashLightManager.killFlashlight();
            mFlashLightManager = null;
        }
        if (mFlash != null) {
            mFlash.close();
            mFlash = null;
        }
    }
}
